package com.example.task.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.ProductItemBinding
import com.example.task.fragment.MainFragmentDirections
import com.example.task.model.Post
import com.example.task.model.PriceDetail
import com.example.task.model.Product
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ProductAdapter(val postList: MutableList<Post>, val priceList: MutableList<PriceDetail>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val list = differ.currentList[position]
        val post = postList[position]
        val price = priceList[position]

        holder.apply {
            Glide.with(holder.itemView).load(list.picUrl).placeholder(R.drawable.ic_placeholder)
                .into(binding.ivProductImage)
            binding.tvProductName.text = list.cmdtyStdName
            binding.tvDate.text = post.createdAt
            binding.tvUpdatedTime.text = post.updatedAt
            binding.tvMinPrice.text = price.minPrice.toString()
            binding.tvMaxPrice.text = price.maxPrice.toString()
            binding.tvPerKg.text = "1/${post.rawReportPriceUnit}"
        }.itemView.setOnClickListener { mView ->
            val direction =
                MainFragmentDirections.actionMainFragmentToRatesFragment(position.toString())
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}