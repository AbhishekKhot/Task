package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.PriceItemBinding
import com.example.task.model.PriceDetail

class RatesAdapter : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    inner class RatesViewHolder(val binding: PriceItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<PriceDetail>() {
        override fun areItemsTheSame(oldItem: PriceDetail, newItem: PriceDetail): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: PriceDetail, newItem: PriceDetail): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val binding = PriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.apply {
            binding.tvMandiLocation.text=list.varietyName
            binding.tvMinMaxPrice.text=list.minPrice.toString() + "-" + list.maxPrice.toString()
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}