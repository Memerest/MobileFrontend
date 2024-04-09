package com.example.memerest.di

import com.example.memerest.presentation.main.ui.collections.CollectionsFragment
import com.example.memerest.presentation.main.ui.error.ErrorFragment
import com.example.memerest.presentation.main.ui.feed.FeedFragment
import com.example.memerest.presentation.main.ui.upload.UploadFragment
import com.example.memerest.presentation.main.ui.weekly.WeeklyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun collectionsFragment(): CollectionsFragment

    @ContributesAndroidInjector
    abstract fun errorFragment(): ErrorFragment

    @ContributesAndroidInjector
    abstract fun feedFragment(): FeedFragment

    @ContributesAndroidInjector
    abstract fun uploadFragment(): UploadFragment

    @ContributesAndroidInjector
    abstract fun weeklyFragment(): WeeklyFragment
}