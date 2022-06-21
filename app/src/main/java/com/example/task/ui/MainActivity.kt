package com.example.task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.repository.GramodayRepository
import com.example.task.util.Resource

class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     lateinit var viewModel: GramodayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title=""

        val repository = GramodayRepository()
        val viewModelProviderFactory = GramodayViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(GramodayViewModel::class.java)


        viewModel.apiData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        it.products.forEach {
                            Glide.with(this).load(it.picUrl).placeholder(R.drawable.ic_placeholder).into(binding.ivImageView)
                        }

                        binding.tvName.text=it.name
                        binding.tvLanguage.text="English"
                        binding.tvLocation.text="${it.loclevel2Name},${it.loclevel3Name}"
                    }
                }
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                }
            }
        })
    }
}