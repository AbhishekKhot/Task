package com.example.task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.repository.GramodayRepository

class MainActivity : AppCompatActivity() {
     lateinit var binding:ActivityMainBinding
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
    }
}