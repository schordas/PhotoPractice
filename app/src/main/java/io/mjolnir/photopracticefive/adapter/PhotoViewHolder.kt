package io.mjolnir.photopracticefive.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.mjolnir.photopracticefive.entities.PhotoUrl
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val picasso by lazy {
        Picasso.get()
    }

    fun bindTo(photo : PhotoUrl) {
        val image = itemView.photo_view

        picasso
            .load(photo.url)
            .fit()
            .into(image)
    }
}