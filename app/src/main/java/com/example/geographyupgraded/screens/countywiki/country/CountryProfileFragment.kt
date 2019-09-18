package com.example.geographyupgraded.screens.countywiki.country


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.geographyupgraded.databinding.CountryProfileFragmentBinding
import com.example.geographyupgraded.factory.BaseViewModelFactory

class CountryProfileFragment : Fragment() {

    private val viewModel: CountryProfileViewModel by lazy {
        val application = requireNotNull(activity).application
        ViewModelProviders.of(this, BaseViewModelFactory { CountryProfileViewModel(application) })
            .get(CountryProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CountryProfileFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel
        return binding.root
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
