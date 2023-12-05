package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Tell the binding object about our viewModel
        binding.viewModel = viewModel

        // Register an observer
        viewModel.navigateToSearch.observe(viewLifecycleOwner, Observer<Boolean>{ navigate ->
            if(navigate){
                // Create navController object
                val navController = findNavController()
                // Navigate to gdgListFragment
                navController.navigate(R.id.action_homeFragment_to_gdgListFragment)
                // Restart the boolean value to falses
                viewModel.onNavigatedToSearch()
            }
        })

        return binding.root
    }
}
