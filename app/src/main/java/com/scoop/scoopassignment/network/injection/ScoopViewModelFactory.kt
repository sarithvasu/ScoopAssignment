package com.scoop.scoopassignment.network.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scoop.scoopassignment.internal.RepositoryInstance
import com.scoop.scoopassignment.ui.HomeViewModel


class ScoopViewModelFactory(private val viewModelType: ScoopViewModelTypeEnum) : ViewModelProvider.NewInstanceFactory(){

    private lateinit var viewModel: ViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if(viewModelType == ScoopViewModelTypeEnum.HomeViewModel){
            HomeViewModel(RepositoryInstance.scoopRepositoryInstance) as T
        }

        else {
            viewModel as T
        }
    }

}





















