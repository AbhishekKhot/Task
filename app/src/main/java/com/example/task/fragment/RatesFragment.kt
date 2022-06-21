package com.example.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.adapter.RatesAdapter
import com.example.task.databinding.FragmentRatesBinding
import com.example.task.ui.GramodayViewModel
import com.example.task.ui.MainActivity
import com.example.task.util.Resource

class RatesFragment : Fragment() {
    private var _binding: FragmentRatesBinding? = null
    private val binding get() = _binding!!
    private val args: RatesFragmentArgs by navArgs()
    private lateinit var viewModel: GramodayViewModel
    private val rateAdapter=RatesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        val position = args.itemPosition

        binding.recyclerView.apply {
            adapter = rateAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.apiData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let {
                        rateAdapter.differ.submitList(it.products[position.toInt()].posts[position.toInt()].priceDetails)
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