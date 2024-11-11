package com.app.cdstore.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cdstore.data.model.UserState
import com.app.cdstore.data.model.SupaBaseAuthViewModel
import com.app.cdstore.data.network.SupabaseClient.client
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    // Estado do usuário que será observado pela UI
    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState
    // Função para iniciar o processo de registro
    fun registerUser(userEmail: String, userPassword: String) {
        // Atualiza o estado para carregando
        _userState.value = UserState.Loading

        // Simulando a lógica de registro (substitua com uma lógica real de autenticação)
        // Pode ser assíncrono se estiver usando uma operação de rede


        viewModelScope.launch {

            client.auth.signUpWith(Email) {
                email = userEmail
                password = userPassword
            }


            try {

                _userState.value = UserState.Success("Registro concluído com sucesso!")
            } catch (e: AuthRestException) {
                // Trata erro específico de autenticação
                _userState.value = UserState.Error("Erro de autenticação: ${e.message}")

            } catch (e: HttpRequestException) {
                // Trata falhas de rede
                _userState.value = UserState.Error("Falha de rede: verifique sua conexão com a internet.")

            } catch (e: Exception) {
                // Trata outros erros
                _userState.value = UserState.Error("Erro inesperado: ${e.message}")
            }

        }


    }



    // Opcional: Função para redefinir o estado, se necessário
    fun resetUserState() {
        _userState.value = UserState.Idle
    }
}
