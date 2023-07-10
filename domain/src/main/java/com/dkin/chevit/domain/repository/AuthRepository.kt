package com.dkin.chevit.domain.repository

import com.dkin.chevit.domain.model.UserState

interface AuthRepository {
    suspend fun getUserState(): UserState
}
