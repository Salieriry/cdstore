package com.app.cdstore.ui.usermenu.address

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.databinding.FragmentAddressBinding


class AddressFragment : Fragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    // Inicializa o AddressViewModel
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla o layout com FragmentRegisterBinding
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração de navegação para voltar
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    /*    // Observa mudanças no endereço (exemplo de uso do ViewModel)
        addressViewModel.addressData.observe(viewLifecycleOwner) { address ->
            binding.addressTextView.text = address // Exemplo de atualização da UI
        }

        // Exemplo de atualização do endereço
        binding.saveAddressButton.setOnClickListener {
            val newAddress = binding.addressEditText.text.toString()
            addressViewModel.updateAddress(newAddress)
        } */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita vazamentos de memória
    }
}
