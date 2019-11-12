package io.mjolnir.photopracticefive.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.mjolnir.photopracticefive.entities.PhotoUrl

@Dao
interface PhotoDao {

    @Query("SELECT * from url_table")
    fun getPhotos() : LiveData<List<PhotoUrl>>

    @Insert
    suspend fun insert(photo : PhotoUrl)
}