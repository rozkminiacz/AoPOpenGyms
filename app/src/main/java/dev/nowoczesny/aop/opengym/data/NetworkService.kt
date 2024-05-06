package dev.nowoczesny.aop.opengym.data

import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkService {
    @GET("/")
    suspend fun getData(): List<Gym>

    @GET("/{id}")
    suspend fun getById(@Path("id") id: String): Gym
}
