package com.dkin.chevit.app.di

import android.content.Context
import com.dkin.chevit.app.R
import com.dkin.chevit.presentation.common.DefaultWebClientIdAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {
    @Provides
    @DefaultWebClientIdAnnotation
    fun provideDefaultWebClientId(
        @ApplicationContext context: Context,
    ): String {
        return context.getString(R.string.default_web_client_id)
    }
}
