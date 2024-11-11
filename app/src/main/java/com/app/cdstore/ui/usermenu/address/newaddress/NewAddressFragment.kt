package com.app.cdstore.ui.usermenu.address.newaddress

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentNewAddressBinding

class NewAddressFragment : Fragment() {

    private var _binding: FragmentNewAddressBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NewAddressFragment()
    }

    private val viewModel: NewAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração de navegação para voltar
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita vazamentos de memória
    }
}

