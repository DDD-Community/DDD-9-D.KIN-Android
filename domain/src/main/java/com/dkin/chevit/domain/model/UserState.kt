package com.dkin.chevit.domain.model

import com.dkin.chevit.domain.base.DomainModel

sealed interface UserState : DomainModel {
    object Guest : UserState

    @JvmInline
    value class User(val token: String) : UserState
}
