package com.firdous.saltpayblank.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.firdous.saltpayblank.R
import com.firdous.saltpayblank.databinding.ActivityAlbumBinding

class AlbumActivity : AppCompatActivity() {

    lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_album)
    }
}