package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.databinding.ProductItemBinding
import com.example.task.model.Post
import com.example.task.model.PriceDetail
import com.example.task.model.Product

class ProductAdapter(val postList:MutableList<Post>,val priceList:MutableList<PriceDetail>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.cmdtyID == newItem.cmdtyID
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val list = differ.currentList[position]
        val post = postList[position]
        val price = priceList[position]

        holder.apply {
            Glide.with(holder.itemView).load(list.picUrl).into(binding.ivProductImage)
            binding.tvProductName.text = list.cmdtyStdName
            binding.tvDate.text = post.dateOfReport
            binding.tvUpdatedTime.text = post.updatedAt
            binding.tvMinPrice.text = price.minPrice.toString()
            binding.tvMaxPrice.text = price.maxPrice.toString()
            binding.tvPerKg.text = "1/${post.rawReportPriceUnit}"
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}