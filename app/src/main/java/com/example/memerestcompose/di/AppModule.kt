package com.example.memerestcompose.di

import com.example.memerestcompose.data.HeaderInterceptor
import com.example.memerestcompose.data.network.PictureService
import com.example.memerestcompose.data.PreferenceStorage
import com.example.memerestcompose.data.network.UserService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_ENDPOINT = "http://31.128.39.67/"
    private const val BASE_ENDPOINT_TEST = "http://localhost:8000/"

    @Provides
    @Singleton
    fun provideOkHttp(
        preferenceStorage: PreferenceStorage,
    ) = OkHttpClient.Builder().apply {
        addInterceptor(HeaderInterceptor(preferenceStorage))
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(loggingInterceptor)
    }
        .connectTimeout(2000L, TimeUnit.MILLISECONDS)
        .readTimeout(2000L, TimeUnit.MILLISECONDS)
        .writeTimeout(2000L, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun providePictureService(
        retrofit: Retrofit,
    ): PictureService {
        return retrofit.create(PictureService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(
        retrofit: Retrofit,
    ): UserService {
        return retrofit.create(UserService::class.java)
    }
}