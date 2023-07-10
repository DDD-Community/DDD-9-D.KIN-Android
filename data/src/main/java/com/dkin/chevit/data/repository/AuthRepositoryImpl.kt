package com.dkin.chevit.data.repository

import com.dkin.chevit.domain.model.UserState
import com.dkin.chevit.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

internal class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {
    override suspend fun getUserState(): UserState {
        return runCatching {
            val token = getIdToken().takeIf { it.isNotBlank() }
            if (token == null) UserState.Guest else UserState.User(token)
        }.getOrDefault(UserState.Guest)
    }

    private suspend fun getIdToken(): String = suspendCancellableCoroutine { continuation ->
        val currentUser = auth.currentUser
        if (currentUser != null) {
            currentUser.getIdToken(true)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val token = task.result?.token
                        continuation.resume(token ?: "")
                    } else {
                        continuation.resume("")
                    }
                }
        } else {
            continuation.resume("")
        }
    }
}
