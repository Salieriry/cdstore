// HomeViewModel.kt
package com.app.cdstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cdstore.data.model.Cd
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _bestSellers = MutableLiveData<List<Cd>>()
    val bestSellers: LiveData<List<Cd>> = _bestSellers

    private val _newAdditions = MutableLiveData<List<Cd>>()
    val newAdditions: LiveData<List<Cd>> = _newAdditions

    init {
        loadBestSellers()
        loadNewAdditions()
    }

    private fun loadBestSellers() {
        viewModelScope.launch {
            try {
                _bestSellers.value = // chamada do repositório ou use case
            } catch (e: Exception) {
                // Tratamento de erro
            }
        }
    }

    private fun loadNewAdditions() {
        viewModelScope.launch {
            try {
                _newAdditions.value = // chamada do repositório ou use case
            } catch (e: Exception) {
                // Tratamento de erro
            }
        }
    }
}
