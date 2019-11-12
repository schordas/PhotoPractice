package io.mjolnir.photopracticefive.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.mjolnir.photopracticefive.entities.PhotoUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [PhotoUrl::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao() : PhotoDao

    companion object {

        var INSTANCE : PhotoDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ) : PhotoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PhotoDatabase::class.java,
                    "url_database"
                )
                    .addCallback(PhotoDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

        private class PhotoDatabaseCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                    scope.launch(Dispatchers.IO) {
                        populateDb(it.photoDao())
                    }
                }
            }
        }

        private suspend fun populateDb(dao: PhotoDao) {
            PhotoRepository(dao).fetchPhotos()
        }
    }
}