package com.example.geographyupgraded.screens.countywiki.countrieslist


import CountryViewBinder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.geographyupgraded.R
import com.example.geographyupgraded.databinding.CountriesListBinding
import com.example.geographyupgraded.factory.BaseViewModelFactory
import com.example.geographyupgraded.network.CountryApiStatus
import com.example.geographyupgraded.screens.countywiki.CountryPresentationModel
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.countries_list.*

class CountriesListFragment : Fragment() {
    private val viewModel: CountriesListViewModel by lazy {
        val application = requireNotNull(activity).application
        ViewModelProviders.of(this, BaseViewModelFactory { CountriesListViewModel(application) })
            .get(CountriesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CountriesListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.constants = CountriesListViewModel.Companion
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val mRecyclerViewAdapter = RendererRecyclerViewAdapter()

        viewModel.countriesToDisplay.observe(this, Observer {
            mRecyclerViewAdapter.setItems(it)
            mRecyclerViewAdapter.notifyDataSetChanged()
        })

        mRecyclerViewAdapter.registerRenderer(
            ViewBinder(
                R.layout.country_view,
                CountryPresentationModel::class.java,
                CountryViewBinder(this.findNavController())
            )
        )

        countries_list_recycle_view.adapter = mRecyclerViewAdapter
        super.onViewCreated(view, savedInstanceState)
    }
}

