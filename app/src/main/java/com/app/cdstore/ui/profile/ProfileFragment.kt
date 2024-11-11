package com.app.cdstore.ui.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.cdstore.databinding.FragmentProfileBinding
import com.app.cdstore.R
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.cdstore.data.model.SupaBaseAuthViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private val profileViewModel by viewModels<ProfileViewModel>()
    private val authViewModel by viewModels<SupaBaseAuthViewModel>()
    private lateinit var profileView: ProfileView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root



        // Initialize Encrypted SharedPreferences
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


        // Load saved credentials if available
       // loadSavedCredentials()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Configure click to open registration screen
        binding.registerContainer.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_register)
        }

        // Initialize ProfileView with onLogin callback for navigation
        profileView = ProfileView(
            binding = binding,
            authViewModel = authViewModel,
            onLogin = {
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply() // Save login state
                findNavController().navigate(R.id.navigation_userMenuFragment)
            },
            profileViewModel = profileViewModel,
            fragment = this
        )
    }

    // Save credentials for future logins
    private fun saveCredentials(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    // Load saved credentials
    private fun loadSavedCredentials() {
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedEmail != null && savedPassword != null) {
            binding.emailInput.setText(savedEmail)
            binding.passwordInput.setText(savedPassword)
        }
    }

    // Validate user input
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
