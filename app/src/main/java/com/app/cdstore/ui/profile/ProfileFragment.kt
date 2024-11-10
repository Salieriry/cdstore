package com.app.cdstore.ui.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.cdstore.databinding.FragmentProfileBinding
import com.app.cdstore.R
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Inicializar SharedPreferences Encriptados
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // Carregar credenciais salvas, se existirem
        loadSavedCredentials()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o clique para abrir a tela de registro
        binding.registerContainer.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_register)
        }
    }

    // Função para salvar as credenciais
    private fun saveCredentials(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    // Função para carregar as credenciais salvas, se existirem
    private fun loadSavedCredentials() {
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedEmail != null && savedPassword != null) {
            binding.emailInput.setText(savedEmail)
            binding.passwordInput.setText(savedPassword)
          //  binding.rememberMeCheckBox.isChecked = true
            // Opcional: Auto-login
            // loginViewModel.login(savedEmail, savedPassword) // Se necessário, adaptar
        }
    }

    // Função para limpar as credenciais
    private fun clearCredentials() {
        with(sharedPreferences.edit()) {
            remove("email")
            remove("password")
            apply()
        }
    }

    // Função para validar os dados de entrada
    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.emailInputLayout.error = "Por favor, insira seu email"
            isValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordInputLayout.error = "Por favor, insira sua senha"
            isValid = false
        } else {
            binding.passwordInputLayout.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}