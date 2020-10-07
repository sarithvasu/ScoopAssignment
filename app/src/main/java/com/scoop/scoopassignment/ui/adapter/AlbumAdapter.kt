package com.scoop.scoopassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scoop.scoopassignment.R
import com.scoop.scoopassignment.network.response.Album
import kotlinx.android.synthetic.main.album_row.view.*


class AlbumAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mAlbumFeeds: List<Album>
    private var count=0





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumFeedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_row,
                parent,
                false
            )
        )
    }



    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // We can have many view holder

        (holder as AlbumFeedViewHolder).bindAlbumFeed(mAlbumFeeds, position)

    }

    fun submitDishData(albums: List<Album>) {
        mAlbumFeeds = albums
        count=albums.count()
    }
}

class AlbumFeedViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
private val MEDIUM=2
    fun bindAlbumFeed(
        albums: List<Album>,
        position: Int
    ) {

        val album = albums[position]
        itemView.tv_album_name.text = album.name
        itemView.tv_artist.text = album.artist
        Glide.with(itemView.context).load(album.image[MEDIUM].text).into(itemView.iv_album_row)
        itemView.setOnClickListener {
            val bundle = bundleOf("album" to album)
            itemView.findNavController().navigate(R.id.albumDetailsFragment,bundle)
        }

    }



}