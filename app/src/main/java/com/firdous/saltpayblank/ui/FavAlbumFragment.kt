package com.firdous.saltpayblank.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.firdous.saltpayblank.R
import com.firdous.saltpayblank.databinding.FragmentFavAlbumBinding
import com.firdous.saltpayblank.util.hide

class FavAlbumFragment : Fragment() {

    private lateinit var binding: FragmentFavAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fav_album, container, false)
        (activity as AlbumActivity).binding.ivFavourite.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}