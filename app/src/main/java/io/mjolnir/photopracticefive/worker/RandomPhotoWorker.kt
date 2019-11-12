package io.mjolnir.photopracticefive.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.mjolnir.photopracticefive.model.PhotoDatabase
import io.mjolnir.photopracticefive.model.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomPhotoWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            val dao = PhotoDatabase.getDatabase(
                applicationContext,
                this
            )
                .photoDao()

            PhotoRepository(dao).fetchRandomPhoto()

            Result.success()
        } catch (error: Throwable) {
            error.printStackTrace()
            Result.failure()
        }
    }

}
