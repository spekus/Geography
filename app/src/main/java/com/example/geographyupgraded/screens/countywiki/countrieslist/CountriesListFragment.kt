package com.example.geographyupgraded.screens.countywiki.countrieslist


import CountryViewBinder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.geographyupgraded.R
import com.example.geographyupgraded.factory.BaseViewModelFactory
import com.example.geographyupgraded.network.models.Country
import com.example.geographyupgraded.screens.countywiki.country.CountryProfileViewModel
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import kotlinx.android.synthetic.main.countries_list.*

/**
 * A simple [Fragment] subclass.
 */
class CountriesListFragment : Fragment() {

    private lateinit var viewModel: CountriesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.countries_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val application = requireNotNull(activity).application
        viewModel = ViewModelProviders.of(
            this,
            BaseViewModelFactory { CountriesListViewModel(application) })
            .get(CountriesListViewModel::class.java)

        val mRecyclerViewAdapter = RendererRecyclerViewAdapter()

        viewModel.countries.observe(this, Observer {
            mRecyclerViewAdapter.setItems(it)
            mRecyclerViewAdapter.notifyDataSetChanged()
        })


        mRecyclerViewAdapter.registerRenderer(
            ViewBinder(
                R.layout.country_view,
                Country::class.java,
                CountryViewBinder(this.findNavController())
            )
        )

        countries_list_recycle_view.adapter = mRecyclerViewAdapter
        super.onViewCreated(view, savedInstanceState)
    }
}

