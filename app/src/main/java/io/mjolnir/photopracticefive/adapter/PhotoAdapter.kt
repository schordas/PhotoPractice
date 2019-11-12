package io.mjolnir.photopracticefive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.mjolnir.photopracticefive.R
import io.mjolnir.photopracticefive.entities.PhotoUrl

class PhotoAdapter : RecyclerView.Adapter<PhotoViewHolder>() {

    private var photos = emptyList<PhotoUrl>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_photo,
                parent,
                false
            )

        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindTo(photos[position])
    }

    fun updateList(photos: List<PhotoUrl>) {
        this.photos = photos
        notifyDataSetChanged()
    }

}