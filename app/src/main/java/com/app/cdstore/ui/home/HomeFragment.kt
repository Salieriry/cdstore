// HomeFragment.kt
package com.app.cdstore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.cdstore.R
import com.app.cdstore.databinding.FragmentHomeBinding
import com.app.cdstore.data.model.Cd
import com.app.cdstore.ui.cdadapter.CdAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import android.widget.LinearLayout

class HomeFragment : Fragment() {



    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val bestSellersAdapter = CdAdapter { cd ->
        navigateToDetails(cd)
    }

    private val newAdditionsAdapter = CdAdapter { cd ->
        navigateToDetails(cd)
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerViews() {
        // Configuração dos RecyclerViews com adapters
        binding.scrollBestSellers.apply {
            (getChildAt(0) as LinearLayout).apply {
                removeAllViews()
            }
        }

        binding.scrollNewAdditions.apply {
            (getChildAt(0) as LinearLayout).apply {
                removeAllViews()
            }
        }
    }

    private fun setupObservers() {
        viewModel.bestSellers.observe(viewLifecycleOwner) { cds ->
            updateBestSellers(cds)
        }

        viewModel.newAdditions.observe(viewLifecycleOwner) { cds ->
            updateNewAdditions(cds)
        }
    }

    private fun setupClickListeners() {
        binding.btnViewAllBestSellers.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToStoreFragment(
                    filterType = "best_sellers"
                )
            )
        }

        binding.btnViewAllNewAdditions.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToStoreFragment(
                    filterType = "new_additions"
                )
            )
        }
    }

    private fun updateBestSellers(cds: List<Cd>) {
        val container = binding.scrollBestSellers.getChildAt(0) as LinearLayout
        container.removeAllViews()

        cds.forEach { cd ->
            val cardView = createCdCardView(cd)
            container.addView(cardView)
        }
    }

    private fun updateNewAdditions(cds: List<Cd>) {
        val container = binding.scrollNewAdditions.getChildAt(0) as LinearLayout
        container.removeAllViews()

        cds.forEach { cd ->
            val cardView = createCdCardView(cd)
            container.addView(cardView)
        }
    }

    private fun createCdCardView(cd: Cd): MaterialCardView {
        val cardBinding = ItemCdBinding.inflate(layoutInflater)
        val card = cardBinding.root as MaterialCardView

        cardBinding.apply {
            txtTitle.text = cd.title
            txtPrice.text = "R$ ${cd.price}"

            Glide.with(imgCd)
                .load(cd.imageUrl)
                .centerCrop()
                .into(imgCd)

            root.setOnClickListener {
                navigateToDetails(cd)
            }
        }

        val params = LinearLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.cd_card_width),
            resources.getDimensionPixelSize(R.dimen.cd_card_height)
        ).apply {
            marginEnd = resources.getDimensionPixelSize(R.dimen.cd_card_margin)
        }
        card.layoutParams = params

        return card
    }

    private fun navigateToDetails(cd: Cd) {
        val action = HomeFragmentDirections.actionHomeFragmentToCdDetailsFragment(cd.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
