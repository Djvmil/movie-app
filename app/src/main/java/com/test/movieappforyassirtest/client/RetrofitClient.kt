package com.test.movieappforyassirtest.client

import com.test.movieappforyassirtest.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
object RetrofitClient {
    val client = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor())
    .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }



}