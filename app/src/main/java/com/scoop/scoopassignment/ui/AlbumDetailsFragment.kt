package com.scoop.scoopassignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.scoop.scoopassignment.R
import com.scoop.scoopassignment.network.response.Album
import kotlinx.android.synthetic.main.album_details_fragment.*
import kotlinx.android.synthetic.main.album_row.view.*


class AlbumDetailsFragment : Fragment() {

private val LARGE = 2

private var mAlbum: Album?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.album_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAlbum=requireArguments().getParcelable("album")
        tv_album_name_details.text=mAlbum?.name
        tv_artist_details.text=mAlbum?.artist
        Glide.with(this).load(mAlbum!!.image[LARGE]?.text).into(iv_album_image_detail)

    }


}