package com.scoop.scoopassignment.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scoop.scoopassignment.ui.HomeViewModel

class ScoopModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, viewModelType: ScoopViewModelTypeEnum): ViewModel {

            if(viewModelType == ScoopViewModelTypeEnum.HomeViewModel){
                val modelFactory = ScoopViewModelFactory(ScoopViewModelTypeEnum.HomeViewModel)
                viewModel = ViewModelProvider(fragment,modelFactory).get(HomeViewModel::class.java)
            }

            return viewModel

        }
    }
}

