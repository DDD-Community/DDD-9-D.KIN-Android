package com.dkin.chevit.data.di

import com.dkin.chevit.data.CoroutineDispatcherProviderImpl
import com.dkin.chevit.domain.base.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SystemModule {
    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        CoroutineDispatcherProviderImpl()
}
