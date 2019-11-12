package io.mjolnir.photopracticefive.model

import androidx.lifecycle.LiveData
import io.mjolnir.photopracticefive.entities.Photo
import io.mjolnir.photopracticefive.entities.PhotoUrl
import io.mjolnir.photopracticefive.network.PhotoService

class PhotoRepository(private val dao: PhotoDao) {

    private val photoService by lazy {
        PhotoService.create()
    }

    val photos : LiveData<List<PhotoUrl>> = dao.getPhotos()

    suspend fun insert(photos: List<Photo.Result>) {
        for (photo in photos) {
            dao.insert(PhotoUrl(photo.urls.regular))
        }
    }

    suspend fun insert(photo: Photo.Result) {
        dao.insert(PhotoUrl(photo.urls.regular))
    }

    suspend fun fetchPhotos() {
        val call = photoService.getPhotos(1) .execute()

        if (call.isSuccessful) {
            val list = call.body()
            val urls : List<Photo>
            if (list != null) {
                insert(list)
            }
        }
    }


    suspend fun fetchRandomPhoto() {
        val call = photoService.getRandomPhoto().execute()

        if (call.isSuccessful) {
            val photo = call.body()
            if (photo != null) {
                insert(photo)
            }
        }
    }
}