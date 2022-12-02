package com.test.movieappforyassirtest.client

import android.util.Log
import com.test.movieappforyassirtest.utils.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestHttpUrl: HttpUrl = request.url

        Log.d(TAG, "intercept: requestHttpUrl = ${requestHttpUrl.query}")

        val url: HttpUrl = requestHttpUrl.newBuilder()
            .addQueryParameter("api_key", Constants.APIKEY)
            .build()

        Log.d(TAG, "intercept: url.query} = ${url.query}")


        // Request customization: add request headers
        val requestBuilder = request.newBuilder()
            .url(url)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")

        Log.d(TAG, "intercept: requestBuilder = ${requestBuilder.toString()}")

        val newRequest = requestBuilder.build()
//        Log.d(TAG, "intercept: requestBuilder = ${newRequest.toString()}")

        return chain.proceed(newRequest)
    }

    companion object{
        private const val TAG = "AuthInterceptor"
    }
}