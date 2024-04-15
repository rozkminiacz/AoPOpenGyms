package dev.nowoczesny.aop.opengym

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

// retrofit - interface

interface NetworkService {
    @GET("/")
    suspend fun getData(): List<Gym>
}

// tworzymy instancje retrofita

class NetworkModule {
    val okHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://opengyms.vercel.app/")
        .client(okHttp)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val networkService: NetworkService = retrofit.create(NetworkService::class.java)
}

@Serializable
data class Gym(
    val id: Int,
    val name: String,
    val address: String,
    val category: String,
    val location: Location,
    val imageUrl: String
)

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)