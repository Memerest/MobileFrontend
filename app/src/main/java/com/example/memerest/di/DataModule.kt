package com.example.memerest.di

import com.example.memerest.data.MemeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMemeRepository(repository: MemeRepository): MemeRepository = MemeRepository()
}