package com.firdous.saltpayblank.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdous.saltpayblank.R
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.databinding.FragmentFavAlbumBinding
import com.firdous.saltpayblank.util.hide
import com.firdous.saltpayblank.util.show
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavAlbumFragment : Fragment() {

    private lateinit var binding: FragmentFavAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter
    private val viewModel: AlbumViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fav_album, container, false)
        (activity as AlbumActivity).binding.ivFavourite.hide()
        (activity as AlbumActivity).binding.toolBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        (activity as AlbumActivity).binding.toolBar.navigationIcon?.setTint(Color.WHITE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        albumAdapter = AlbumAdapter(requireContext()){ viewModel.setFavourite(it)}
        binding.rvFavourite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = albumAdapter
        }
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.albumStateFlow.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.show()
                    }
                    is Resource.Success -> {
                        binding.progressBar.hide()
                        albumAdapter.submitList(it.data.filter { album-> album.favourite == true })
                    }
                    is Resource.Error -> {
                        binding.progressBar.hide()
                        Timber.e(it.message)
                    }
                }
            }
        }
    }

}