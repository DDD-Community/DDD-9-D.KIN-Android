package com.dkin.chevit.data.di

import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.provider.WebAuthProvider
import com.dkin.chevit.data.R
import com.dkin.chevit.data.di.annotation.AuthClientId
import com.dkin.chevit.data.di.annotation.AuthDomain
import com.dkin.chevit.data.di.annotation.AuthScheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {
    @AuthClientId
    @Provides
    fun provideAuthClientId(
        @ApplicationContext context: Context,
    ): String {
        return context.getString(R.string.com_auth0_client_id)
    }

    @AuthDomain
    @Provides
    fun provideAuthDomain(
        @ApplicationContext context: Context,
    ): String {
        return context.getString(R.string.com_auth0_domain)
    }

    @AuthScheme
    @Provides
    fun provideAuthScheme(
        @ApplicationContext context: Context,
    ): String {
        return context.getString(R.string.com_auth0_scheme)
    }

    @Provides
    fun provideAuthInfo(
        @AuthClientId clientId: String,
        @AuthDomain domain: String,
    ): Auth0 {
        return Auth0(clientId, domain)
    }

    @Provides
    fun provideWebAuthProvider(
        auth0: Auth0,
        @AuthScheme scheme: String,
    ): WebAuthProvider.Builder {
        return WebAuthProvider.login(auth0).withScheme(scheme)
    }

    @Provides
    fun provideAuthenticationAPIClient(
        auth0: Auth0,
    ): AuthenticationAPIClient {
        return AuthenticationAPIClient(auth0)
    }

    @Provides
    fun provideSharedPreferencesStorage(
        @ApplicationContext context: Context,
    ): SharedPreferencesStorage {
        return SharedPreferencesStorage(context)
    }

    @Provides
    fun provideCredentialsManager(
        authenticationAPIClient: AuthenticationAPIClient,
        sharedPreferencesStorage: SharedPreferencesStorage,
    ): CredentialsManager {
        return CredentialsManager(authenticationAPIClient, sharedPreferencesStorage)
    }
}
