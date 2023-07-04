package com.dkin.chevit.data.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthClientId

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthDomain

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthScheme
