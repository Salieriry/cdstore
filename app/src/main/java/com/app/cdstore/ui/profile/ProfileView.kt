package com.app.cdstore.ui.profile

import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.app.cdstore.data.model.SupaBaseAuthViewModel
import com.app.cdstore.data.model.UserState
import com.app.cdstore.databinding.FragmentProfileBinding
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class ProfileView(
    private val binding: FragmentProfileBinding,
    private val onLogin: () -> Unit,
    private val authViewModel: SupaBaseAuthViewModel,
    private val profileViewModel: ProfileViewModel,
    private val fragment: Fragment
) {

    private val sharedPreferences: SharedPreferences

    init {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            fragment.requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        setupListeners()
        observeProfileViewModel(fragment.viewLifecycleOwner)
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (validateInput(email, password)) {
                profileViewModel.loginUser(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
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

        return isValid
    }

    private fun observeProfileViewModel(lifecycleOwner: LifecycleOwner) {
        profileViewModel.userState.observe(lifecycleOwner) { state ->
            when (state) {
                is UserState.Idle -> {
                    binding.progressBar.visibility = View.GONE // Optional: handle Idle state
                }
                is UserState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UserState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(fragment.requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    saveLoginState()  // Save login state upon successful login
                    if (sharedPreferences.getBoolean("isLoggedIn", false)){
                        onLogin()// Trigger navigation to user menu
                    }

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

    private fun saveLoginState() {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }

    fun logout() {
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }
}
