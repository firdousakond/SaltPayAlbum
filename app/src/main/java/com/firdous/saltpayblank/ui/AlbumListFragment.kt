package com.firdous.saltpayblank.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdous.saltpayblank.R
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.databinding.FragmentAlbumBinding
import com.firdous.saltpayblank.util.hide
import com.firdous.saltpayblank.util.show
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AlbumListFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter
    private var albumList: MutableList<AlbumEntity>? = null
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
        binding.etSearch.addTextChangedListener { searchAlbum(it?.trim()?.toString()) }
        setupAdapter()
        setupObserver()
    }
    private fun searchAlbum(text: String?) {
        if (!text.isNullOrEmpty()) {
            val searchList = albumList?.filter { it.name?.label?.lowercase()?.contains(text.lowercase()) == true }
            albumAdapter.submitList(searchList)
        } else {
            albumAdapter.submitList(albumList)
        }
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

    private fun updateList(data: List<AlbumEntity>) {
        albumList = data.toMutableList()
        albumAdapter.submitList(data)
    }
}