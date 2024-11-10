package com.app.cdstore.ui.register

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.app.cdstore.data.model.UserState
import com.app.cdstore.databinding.FragmentRegisterBinding
import com.app.cdstore.data.model.SupaBaseAuthViewModel


class RegisterView(
    private val binding: FragmentRegisterBinding,
    private val onBackClicked: () -> Unit,
    private val authViewModel: SupaBaseAuthViewModel,
    private val registerViewModel: RegisterViewModel,
    private val fragment: Fragment
) {

    init {
        setupListeners()
        observeRegisterViewModel(fragment)
    }

    private fun setupListeners() {
        // Botão de retorno para a tela anterior
        binding.backButton.setOnClickListener {
            onBackClicked()
        }

        // Botão de registro
        binding.registerButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val confirmPassword = binding.confirmPasswordInput.text.toString().trim()

            if (validateInput(email, password, confirmPassword)) {
                registerViewModel.registerUser(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.emailInputLayout.error = "Por favor, insira um email"
            isValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordInputLayout.error = "Por favor, insira uma senha"
            isValid = false
        } else {
            binding.passwordInputLayout.error = null
        }

        if (password != confirmPassword) {
            binding.confirmPasswordInputLayout.error = "As senhas não coincidem"
            isValid = false
        } else {
            binding.confirmPasswordInputLayout.error = null
        }

        return isValid
    }

    private fun observeRegisterViewModel(lifecycleOwner: LifecycleOwner) {
        registerViewModel.userState.observe(lifecycleOwner) { state ->
            when (state) {
                is UserState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UserState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(fragment.requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    onBackClicked()  // Retorna à tela anterior após sucesso
                }
                is UserState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(fragment.requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}
