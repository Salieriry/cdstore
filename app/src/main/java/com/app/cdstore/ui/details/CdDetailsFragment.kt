package com.app.cdstore.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.cdstore.databinding.FragmentCdDetailsBinding
import com.app.cdstore.data.model.Cd  // Importando o modelo Cd da pasta correta
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.app.cdstore.R

class CdDetailsFragment : Fragment() {

    private var _binding: FragmentCdDetailsBinding? = null
    private val binding get() = _binding!!



 //   private val args: CdDetailsFragmentArgs by navArgs()
  //  val cdId = args.cdId
    private val viewModel: CdDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCdDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração de navegação para voltar
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Configura o clique para abrir a tela de carrinho
        binding.btnAddToCart.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_cart)
        }

        //setupToolbar()
        //setupObservers()
       // loadData()
    }

  /*  private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        viewModel.cd.observe(viewLifecycleOwner) { cd ->
            cd?.let { updateUi(it) }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            // Implementar loading state, se necessário
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let { showError(it) }
        }
    }

    private fun loadData() {
        viewModel.loadCdDetails(args.cdId)
    }

    private fun updateUi(cd: Cd) {
        binding.apply {
            Glide.with(requireContext())
                .load(cd.imageUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgCd)

            collapsingToolbar.title = cd.title
            txtTitle.text = cd.title
            txtPrice.text = "R$ ${cd.price}"
            txtArtist.text = cd.artist

            cd.genre?.let { genre ->
                txtGenre.text = "Gênero: $genre"
            }

            cd.year?.let { year ->
                txtYear.text = "Ano: $year"
            }

            cd.description?.let { description ->
                txtDescription.text = description
            }

            btnAddToCart.setOnClickListener {
                viewModel.addToCart()
                showAddedToCartMessage()
            }
        }
    }

    private fun showAddedToCartMessage() {
        Snackbar.make(
            binding.root,
            "CD adicionado ao carrinho",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showError(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    } */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
