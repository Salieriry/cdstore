// CdAdapter.kt
package com.app.cdstore.ui.cdadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.cdstore.data.model.Cd
import com.app.cdstore.databinding.ItemCdBinding
import com.bumptech.glide.Glide

class CdAdapter(
    private val onItemClick: (Cd) -> Unit
) : RecyclerView.Adapter<CdAdapter.CdViewHolder>() {

    private var items = listOf<Cd>()

    fun updateItems(newItems: List<Cd>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdViewHolder {
        val binding = ItemCdBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CdViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CdViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CdViewHolder(
        private val binding: ItemCdBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cd: Cd) {
            binding.apply {
                txtTitle.text = cd.title
                txtPrice.text = "R$ ${cd.price}"

                // Carregar imagem usando Glide
                Glide.with(imgCd)
                    .load(cd.imageUrl)
                    .centerCrop()
                    .into(imgCd)

                root.setOnClickListener {
                    onItemClick(cd)
                }
            }
        }
    }
}
