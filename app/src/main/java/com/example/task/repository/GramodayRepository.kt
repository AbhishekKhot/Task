package com.example.task.repository

import com.example.task.api.RetrofitInstance


class GramodayRepository {
    suspend fun getData() = RetrofitInstance.api.getData()
}