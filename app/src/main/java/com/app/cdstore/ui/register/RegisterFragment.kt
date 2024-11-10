package com.app.cdstore.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.cdstore.databinding.FragmentRegisterBinding
import com.app.cdstore.data.model.SupaBaseAuthViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    // Certifique-se de que os ViewModels estão corretamente inicializados
    private val registerViewModel by viewModels<RegisterViewModel>()
    private val authViewModel by viewModels<SupaBaseAuthViewModel>()
    private lateinit var registerView: RegisterView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa a View com os elementos de UI
        registerView = RegisterView(
            binding = binding,
            onBackClicked = { findNavController().popBackStack() },
            authViewModel = authViewModel,
            registerViewModel = registerViewModel,
            fragment = this
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
