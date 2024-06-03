package com.example.memerestcompose.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class HeaderInterceptor(private val preferenceStorage: PreferenceStorage) : Interceptor {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "bearer"
    }

    private val token: String by lazy { preferenceStorage.userToken }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = if (!original.url.encodedPath.contains("auth") || token.isBlank()) {
            original.newBuilder().header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        } else {
            original.newBuilder()
        }.method(original.method, original.body).build()

        return chain.proceed(request)
    }
}