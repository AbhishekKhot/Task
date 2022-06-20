package com.example.task.api

import com.example.task.model.GramodayApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GramodayAPI {

    @GET("v1/user/bySlug")
    suspend fun getData(
        @Query("profileSlug")
        profile: String = "mmtradingco",
    ): Response<GramodayApiResponse>

}