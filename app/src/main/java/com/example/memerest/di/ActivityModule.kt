package com.example.memerest.di

import com.example.memerest.presentation.login.LoginActivity
import com.example.memerest.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideLoginActivity(): LoginActivity
}