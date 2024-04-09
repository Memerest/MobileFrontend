package com.example.memerest.di

import android.content.Context
import com.example.memerest.MyApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: MyApplication): Context {
        return app.applicationContext
    }
}