package com.firdous.saltpayblank.domain

import com.firdous.saltpayblank.data.local.entity.*
import com.firdous.saltpayblank.data.remote.model.Entry

fun List<Entry>.toAlbumEntity(): List<AlbumEntity> {
   return this.map { entry->
       val imageEntityList : MutableList<ImImageEntity> = ArrayList()
       entry.image?.forEach {
           imageEntityList.add(ImImageEntity(label = it.label))
       }
       AlbumEntity(
           id = entry.id.attributes?.id?.toInt()?:0,
           artist = ImArtistEntity(label = entry.artist?.label),
           image = imageEntityList,
           itemCount = ImItemCountEntity(label = entry.itemCount?.label),
           name = ImNameEntity(label = entry.name?.label),
           price = ImPriceEntity(label = entry.price?.label),
           releaseDate = ImReleaseDateEntity(
               label = entry.releaseDate?.label,
               attributes = AttributesEntity(entry.releaseDate?.attributes?.label)
           ),
           title = TitleEntity(label = entry.title?.label),
           favourite = false
       )
   }
}