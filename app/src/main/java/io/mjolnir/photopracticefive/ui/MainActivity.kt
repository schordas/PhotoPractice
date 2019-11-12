package io.mjolnir.photopracticefive.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.mjolnir.photopracticefive.R
import io.mjolnir.photopracticefive.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recycler_view
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val adapter = PhotoAdapter()

        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)
            .get(PhotoListViewModel::class.java)

        viewModel.photos.observe(this, Observer { photos ->
            adapter.updateList(photos)
        })

        viewModel.scheduleRandomPhotoWorker()
    }
}
