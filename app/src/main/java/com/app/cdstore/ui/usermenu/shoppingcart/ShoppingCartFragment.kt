package com.app.cdstore.ui.usermenu.shoppingcart

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentClientDataBinding
import com.app.cdstore.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }

    private val viewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração de navegação para voltar
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Configura o clique para abrir a tela de checkout
        binding.checkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_checkout)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita vazamentos de memória
    }
}
