package com.diegoferreiracaetano.chuckNorris.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.diegoferreiracaetano.chuckNorris.R
import com.diegoferreiracaetano.chuckNorris.databinding.FragmentMainBinding
import com.diegoferreiracaetano.chuckNorris.ui.main.adapter.CategoriesListAdapter
import com.diegoferreiracaetano.chuckNorris.util.lazyThreadSafetyNone
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment : DaggerFragment(),CategoriesListAdapter.Callbacks{

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel by lazyThreadSafetyNone {
        activity?.let { ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.eventCallbacks = this
        return binding.root
    }


    override fun onItemClick(view: View, item: String) {
        val bundle = Bundle()
        bundle.putString("category", item)
        Navigation.findNavController(view).navigate(R.id.action_next, bundle)
    }
}
