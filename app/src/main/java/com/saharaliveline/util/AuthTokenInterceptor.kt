package com.saharaliveline.util

import com.saharaliveline.data.preferences.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adding authorization on all request
 */
class AuthTokenInterceptor(private val appPreferences: AppPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().apply {
            addHeader("authorization", appPreferences.getAuthToken() ?: "")
        }.build()
        return chain.proceed(request)
    }
}