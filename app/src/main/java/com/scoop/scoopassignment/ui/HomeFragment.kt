package com.scoop.scoopassignment.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scoop.scoopassignment.R
import com.scoop.scoopassignment.internal.ScopedFragment
import com.scoop.scoopassignment.network.injection.ScoopModelGenerator
import com.scoop.scoopassignment.network.injection.ScoopViewModelTypeEnum
import com.scoop.scoopassignment.network.response.SearchResult
import com.scoop.scoopassignment.ui.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : ScopedFragment() {


    private lateinit var mAlbumAdapter: AlbumAdapter
    private lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initRecyclerView()




    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_search_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val txtSearch = searchView.findViewById(R.id.search_src_text) as EditText
            txtSearch.hint = resources.getString(R.string.search_menu_title)
            txtSearch.setHintTextColor(Color.LTGRAY)
            txtSearch.setTextColor(Color.BLACK)
            val searchplate =
                searchView.findViewById(androidx.appcompat.R.id.search_plate) as View
            searchplate.setBackgroundResource(R.drawable.border_white_rounded)

            val searchCloseIcon: ImageView =
                searchView.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
            searchCloseIcon.setImageResource(R.drawable.ic_close)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(text: String?): Boolean = true

                override fun onQueryTextChange(text: String?): Boolean {
                    updateSearch(text!!)
                    return true
                }

            })
        }

    }

    private fun initRecyclerView() {
        mAlbumAdapter = AlbumAdapter()
        rv_album_list.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            adapter = mAlbumAdapter
        }
    }

    private fun updateSearch(query: String) {
        val queryParameterMap = mapOf("album" to query)
        mViewModel = ScoopModelGenerator.getModel(
            this,
            ScoopViewModelTypeEnum.HomeViewModel
        ) as HomeViewModel
        launch(Dispatchers.Main) {
            try {
                val searchResponse = mViewModel.searchResult(queryParameterMap)
                searchResponse.observe(viewLifecycleOwner, observable)
            } catch (e: Exception) {
                Log.e("ERROR", "${e.localizedMessage}S")

            }
        }
    }

    private val observable = Observer<SearchResult> {
        if (it == null) return@Observer
        mAlbumAdapter.submitDishData(it.results.albummatches.album)
        mAlbumAdapter.notifyDataSetChanged()
    }
}