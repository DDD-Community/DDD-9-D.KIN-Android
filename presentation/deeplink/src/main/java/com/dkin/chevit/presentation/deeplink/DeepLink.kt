package com.dkin.chevit.presentation.deeplink

import android.net.Uri

sealed interface DeepLink {
    val deepLink: String
    val uri: Uri
        get() = Uri.parse(deepLink)

    object Auth : DeepLink {
        override val deepLink: String = "$SCHEME://auth"
    }

    object Home : DeepLink {
        override val deepLink: String = "$SCHEME://home"
    }

    companion object {
        private const val SCHEME = "chevit"
    }
}
