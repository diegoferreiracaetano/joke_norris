package com.diegoferreiracaetano.chuckNorris.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.diegoferreiracaetano.chuckNorris.R
import com.diegoferreiracaetano.chuckNorris.databinding.FragmentDetailBinding
import com.diegoferreiracaetano.chuckNorris.ui.MainActivity
import com.diegoferreiracaetano.chuckNorris.util.lazyThreadSafetyNone
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    private lateinit var binding: FragmentDetailBinding

    companion object {
        const val ARG_TYPE = "type"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(activity!!, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        binding.let {
            it.setLifecycleOwner(this@DetailFragment)
            it.viewModel = viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DetailFragmentArgs.fromBundle(arguments).let {
            (activity as MainActivity).supportActionBar?.title = it.category.capitalize()
            viewModel.getJoke(it.category)
        }

    }
}
