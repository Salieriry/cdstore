package com.app.cdstore.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cdstore.data.repository.CdRepository
import com.app.cdstore.data.model.Cd
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
  //  private val repository = CdRepository()
                                ) : ViewModel() {

     // Você precisa criar este repositório

    private val _cds = MutableLiveData<List<Cd>>()
    val cds: LiveData<List<Cd>> = _cds

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

  /*  private var currentFilter = "popularity"
    private var currentSearch = ""
    private var isAscending = true

    init {
        loadCds()
    }

    fun searchCds(query: String) {
        currentSearch = query
        updateList()
    }

    fun applyFilter(filter: String) {
        currentFilter = filter
        updateList()
    }

    fun toggleSortOrder() {
        isAscending = !isAscending
        updateList()
    }

    fun errorShown() {
        _error.value = null
    }

    private fun loadCds() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val cdList = repository.getCds()
                _cds.value = cdList
                _error.value = null
            } catch (e: IOException) {
                _error.value = "Erro ao carregar os CDs: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun updateList() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                var filteredList = repository.getCds()

                // Aplicar busca
                if (currentSearch.isNotEmpty()) {
                    filteredList = filteredList.filter { cd ->
                        cd.title.contains(currentSearch, ignoreCase = true) ||
                                cd.artist.contains(currentSearch, ignoreCase = true)
                    }
                }

                // Aplicar filtro
                val sortedList = when (currentFilter) {
                    "popularity" -> filteredList.sortedByDescending { it.popularity }
                    "alphabetical" -> filteredList.sortedBy { it.title }
                    "artist" -> filteredList.sortedBy { it.artist }
                    "year" -> filteredList.sortedByDescending { it.year }
                    else -> filteredList
                }

                // Aplicar ordem
                _cds.value = if (isAscending) sortedList else sortedList.reversed()

            } catch (e: IOException) {
                _error.value = "Erro ao atualizar a lista: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    } */
}