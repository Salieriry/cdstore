package com.app.cdstore.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cdstore.data.model.Cd  // Importando o modelo Cd da pasta correta
import kotlinx.coroutines.launch

class CdDetailsViewModel : ViewModel() {
    private val _cd = MutableLiveData<Cd>()
    val cd: LiveData<Cd> = _cd

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadCdDetails(cdId: Long) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                // Aqui você chamaria seu repository
                // _cd.value = repository.getCdDetails(cdId)
            } catch (e: Exception) {
                _error.value = "Erro ao carregar detalhes do CD"
            } finally {
                _loading.value = false
            }
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            try {
                cd.value?.let { cd ->
                    // Aqui você chamaria seu repository para adicionar ao carrinho
                    // repository.addToCart(cd)
                }
            } catch (e: Exception) {
                _error.value = "Erro ao adicionar ao carrinho"
            }
        }
    }
}
