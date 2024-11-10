package com.app.cdstore.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.cdstore.databinding.FragmentStoreBinding
//import com.app.cdstore.ui.cdadapter.CdAdapter
import com.google.android.material.snackbar.Snackbar


class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoreViewModel by viewModels()
  //  private val args: StoreFragmentArgs by navArgs()
   // private lateinit var cdAdapter: CdAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  setupRecyclerView()
       // setupSearchAndFilters()
       // setupObservers()
       // applyInitialFilter()
    }

  /*  private fun setupRecyclerView() {
        cdAdapter = CdAdapter { cd ->
            // Navegação para detalhes do CD
            findNavController().navigate(
                StoreFragmentDirections.actionStoreToCdDetails(cdId = cd.id)
            )
        }

        binding.cdRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = cdAdapter
        }
    }

    private fun setupSearchAndFilters() {
        // Configurar busca
        binding.searchInput.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchCds(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchCds(it) }
                return true
            }
        })

        // Configurar chips de filtro
        binding.filterChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.popularityChip.id -> viewModel.applyFilter("popularity")
                binding.alphabeticalChip.id -> viewModel.applyFilter("alphabetical")
                binding.artistChip.id -> viewModel.applyFilter("artist")
                binding.yearChip.id -> viewModel.applyFilter("year")
            }
        }

        // Configurar botão de ordenação
        binding.sortOrderButton.setOnClickListener {
            viewModel.toggleSortOrder()
        }
    }

    private fun setupObservers() {
        viewModel.cds.observe(viewLifecycleOwner) { cds ->
            cdAdapter.submitList(cds)
            binding.text_store.visibility = if (cds.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                viewModel.errorShown()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Implementar loading state se necessário
        }
    }

    private fun applyInitialFilter() {
        when (args.filterType) {
            "best_sellers" -> {
                binding.popularityChip.isChecked = true
                viewModel.applyFilter("popularity")
            }
            "new_additions" -> {
                binding.yearChip.isChecked = true
                viewModel.applyFilter("year")
            }
            else -> {
                binding.popularityChip.isChecked = true
                viewModel.applyFilter("popularity")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    } */
}