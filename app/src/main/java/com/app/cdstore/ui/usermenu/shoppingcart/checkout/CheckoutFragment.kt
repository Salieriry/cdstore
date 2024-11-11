package com.app.cdstore.ui.usermenu.shoppingcart.checkout

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentCheckoutBinding
import com.app.cdstore.databinding.FragmentClientDataBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CheckoutFragment()
    }

    private val viewModel: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração de navegação para voltar
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Configura o clique para confirmar a compra e ir para a tela home
        binding.confirmPurchaseButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkout_to_home)
        }

        // Configura o clique para selecionar o endereço
        binding.changeAddressButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkout_to_addresses)
        }

        // Configura o clique para adicionar um novo cartão de crédito
        binding.addCardButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkout_to_new_credit_card)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita vazamentos de memória
    }
}
