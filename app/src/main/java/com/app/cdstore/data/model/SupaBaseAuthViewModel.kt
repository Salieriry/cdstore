package com.app.cdstore.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.cdstore.utils.SharedPreferenceHelper
import io.github.jan.supabase.auth.auth
import com.app.cdstore.data.network.SupabaseClient.client
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.SupabaseEncodingException
import kotlinx.coroutines.launch

class SupaBaseAuthViewModel(application: Application) :AndroidViewModel(application) {

    private val _userState = MutableLiveData<UserState>(UserState.Idle)
    val userState: LiveData<UserState> get() = _userState

    fun signUp(userEmail: String, userPassword: String) {
        _userState.value = UserState.Loading

        viewModelScope.launch {
            try {


                // Lógica de registro
                client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }



                // Salva o token após o cadastro bem-sucedido
                saveToken()

                // Atualiza o estado para sucesso no registro
                _userState.value = UserState.Success("Cadastro realizado com sucesso")

            } catch (e: AuthRestException) {
                _userState.value = UserState.Error("Erro de autenticação: ${e.message}")

            } catch (e: HttpRequestException) {
                _userState.value = UserState.Error("Falha de rede: verifique sua conexão com a internet.")

            } catch (e: SupabaseEncodingException) {
                _userState.value = UserState.Error("Erro de encoding ao processar dados.")

            } catch (e: Exception) {
                _userState.value = UserState.Error("Erro inesperado: ${e.message}")
            }
        }
    }

    private fun saveToken() {
        viewModelScope.launch {
            try {
                val accessToken = client.auth.currentAccessTokenOrNull()
                if (accessToken != null) {
                    val sharedPref = SharedPreferenceHelper(getApplication())
                    sharedPref.saveStringData("accessToken", accessToken)
                }
            } catch (e: SupabaseEncodingException) {
                _userState.value = UserState.Error("Erro de encoding ao salvar o token.")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Erro ao salvar o token: ${e.message}")
            }
        }
    }



}