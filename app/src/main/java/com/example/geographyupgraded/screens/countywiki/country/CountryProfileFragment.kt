package com.example.geographyupgraded.screens.countywiki.country


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.geographyupgraded.databinding.CountryProfileFragmentBinding
import com.example.geographyupgraded.factory.BaseViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CountryProfileFragment : Fragment() {

    @Inject
    lateinit var repository: CountryRepository

    private val viewModel: CountryProfileViewModel by lazy {
        val application = requireNotNull(activity).application
        ViewModelProviders.of(this, BaseViewModelFactory { CountryProfileViewModel(application, repository) })
            .get(CountryProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CountryProfileFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val safeArgs = CountryProfileFragmentArgs.fromBundle(arguments)
            val country = safeArgs.country
            viewModel.loadCountry(country)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
