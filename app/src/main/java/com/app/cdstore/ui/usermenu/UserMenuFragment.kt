package com.app.cdstore.ui.usermenu

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentUserMenuBinding
import android.content.SharedPreferences

class UserMenuFragment : Fragment() {

    private var _binding: FragmentUserMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserMenuViewModel by viewModels()

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o SharedPreferences para verificar o login
        sharedPreferences = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserMenuBinding.inflate(inflater, container, false)

        // Verifica se o usuário está logado
        /* val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

         // Se não estiver logado, redireciona para o login
          if (!isLoggedIn) {
             findNavController().navigate(R.id.navigation_profile)
         } */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura os cliques dos botões
        binding.personalDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_personal_data)
        }

        binding.addressButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_addresses)
        }

        binding.creditCardButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_credit_cards)
        }

        binding.cartButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_shopping_cart)
        }

        binding.cartViewButton.setOnClickListener {
            findNavController().navigate(R.id.action_user_menu_to_shopping_cart)
        }

        // Botão de logout
        binding.logoutButton.setOnClickListener {
            // Limpa o estado de login ao clicar no logout
            sharedPreferences.edit().apply {
                putBoolean("isLoggedIn", false)
                remove("email")
                remove("password")
                remove("accesstoken")
                apply()
            }
            // Redireciona para a tela de login após logout
            findNavController().navigate(R.id.action_user_menu_to_profile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
