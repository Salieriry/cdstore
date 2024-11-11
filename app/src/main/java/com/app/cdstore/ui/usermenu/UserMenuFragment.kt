package com.app.cdstore.ui.usermenu

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentUserMenuBinding

class UserMenuFragment : Fragment() {

    private var _binding: FragmentUserMenuBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = UserMenuFragment()
    }

    private val viewModel: UserMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel if necessary
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o clique para abrir a tela de dados do cliente
        binding.personalDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_personal_data)
        }

        // Configura o clique para abrir a tela de endereços
        binding.addressButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_addresses)
        }

        // Configura o clique para abrir a tela de cartões de crédito
        binding.creditCardButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_credit_cards)
        }

        // Configura o clique para abrir a tela de carrinho
        binding.cartButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_shopping_cart)
        }

        // Configura o clique para abrir a tela de carrinho
        binding.cartViewButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_shopping_cart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding para evitar vazamento de memória
    }
}