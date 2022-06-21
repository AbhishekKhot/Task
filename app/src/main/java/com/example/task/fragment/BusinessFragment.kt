package com.example.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.adapter.ProductAdapter
import com.example.task.databinding.FragmentBusinessBinding
import com.example.task.model.Post
import com.example.task.model.PriceDetail
import com.example.task.ui.GramodayViewModel
import com.example.task.ui.MainActivity
import com.example.task.util.Resource

class BusinessFragment : Fragment() {
    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel: GramodayViewModel
    private lateinit var postList: MutableList<Post>
    private lateinit var priceList: MutableList<PriceDetail>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        postList = mutableListOf()
        priceList = mutableListOf()

        productAdapter = ProductAdapter(postList, priceList)

        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        viewModel.apiData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let {
                        productAdapter.differ.submitList(it.products)

                        binding.tvMarketName.text = it.business.marketStdName
                        binding.tvFirmName.text = it.business.firmName
                        binding.tvShopNumber.text = it.business.mandiShopnum

                        it.products.forEach {
                            it.posts.forEach {
                                postList.add(it)
                                it.priceDetails.forEach {
                                    priceList.add(it)
                                }
                            }
                        }
                        productAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }
}