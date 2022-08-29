package hr.isinkovic.kickbase.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MatchesService {
    @GET("v2/competitions/1/matches")
    suspend fun getMatches(): Response<MatchesResponse>

    companion object {
        private const val BASE_URL = "https://apidev.kickbase.com/"

        fun create(): MatchesService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MatchesService::class.java)
        }
    }
}