package com.firdous.saltpayblank.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdous.saltpayblank.R
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.model.AlbumsResponse
import com.firdous.saltpayblank.databinding.FragmentAlbumBinding
import com.firdous.saltpayblank.util.hide
import com.firdous.saltpayblank.util.show
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AlbumListFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter
    private val viewModel: AlbumViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)
        (activity as AlbumActivity).binding.toolBar.navigationIcon = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        albumAdapter = AlbumAdapter(requireContext())
        binding.rvAlbum.apply {
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
                        updateList(it.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.hide()
                        Timber.e(it.message)
                    }
                }
            }
        }
    }

    private fun updateList(data: AlbumsResponse) {
        val entry = data.feed?.entry
        albumAdapter.submitList(entry)
    }
}