package com.dkin.chevit.data.repository

import android.content.Context
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.dkin.chevit.domain.model.UserState
import com.dkin.chevit.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val credentialsManager: CredentialsManager,
    private val builder: WebAuthProvider.Builder,
) : AuthRepository {
    override suspend fun getUserState(): UserState {
        return runCatching {
            val token = getAccessToken()
            UserState.User(token)
        }.getOrDefault(UserState.Guest)
    }

    private suspend fun getAccessToken(): String = withTimeout(TIMEOUT) {
        suspendCancellableCoroutine { continuation ->
            val callback = object : Callback<Credentials, CredentialsManagerException> {
                override fun onSuccess(credentials: Credentials) {
                    continuation.resume(credentials.accessToken)
                }

                override fun onFailure(error: CredentialsManagerException) {
                    continuation.resumeWithException(error)
                }
            }
            credentialsManager.getCredentials(callback)
        }
    }

    override suspend fun signIn(): UserState {
        return runCatching {
            val token = processSignIn()
            return UserState.User(token)
        }.getOrDefault(UserState.Guest)
    }

    private suspend fun processSignIn(): String = withTimeout(TIMEOUT) {
        suspendCancellableCoroutine { continuation ->
            val callback = object : Callback<Credentials, AuthenticationException> {
                override fun onSuccess(credentials: Credentials) {
                    continuation.resume(credentials.accessToken)
                }

                override fun onFailure(error: AuthenticationException) {
                    continuation.resumeWithException(error)
                }
            }
            builder.start(context, callback)
        }
    }

    companion object {
        private const val TIMEOUT = 15_000L
    }
}
