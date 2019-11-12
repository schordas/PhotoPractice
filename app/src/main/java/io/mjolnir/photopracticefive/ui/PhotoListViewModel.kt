package io.mjolnir.photopracticefive.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.mjolnir.photopracticefive.RANDOM_PHOTO_WORKER
import io.mjolnir.photopracticefive.entities.PhotoUrl
import io.mjolnir.photopracticefive.model.PhotoDatabase
import io.mjolnir.photopracticefive.model.PhotoRepository
import io.mjolnir.photopracticefive.worker.RandomPhotoWorker
import java.util.concurrent.TimeUnit

class PhotoListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PhotoRepository

    val photos: LiveData<List<PhotoUrl>>

    init {
        val dao = PhotoDatabase.getDatabase(
            application.applicationContext,
            viewModelScope
        )
            .photoDao()

        repository = PhotoRepository(dao)

        photos = repository.photos
    }


    fun scheduleRandomPhotoWorker() {

        val worker = PeriodicWorkRequestBuilder<RandomPhotoWorker>(
            15,
            TimeUnit.MINUTES)
            .build()

        WorkManager
            .getInstance(getApplication())
            .enqueueUniquePeriodicWork(RANDOM_PHOTO_WORKER,
                ExistingPeriodicWorkPolicy.KEEP,
                worker)
    }
}