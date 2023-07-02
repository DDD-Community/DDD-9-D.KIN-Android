package com.dkin.chevit.app

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.dkin.chevit.app.databinding.ActivityMainBinding
import com.dkin.chevit.core.BaseActivity
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initView() = binding {
        btnAuth.setOnClickListener {
            val account = Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain),
            )
            WebAuthProvider.login(account)
                .withScheme(getString(R.string.com_auth0_scheme))
                .withScope("openid profile email")
                .start(
                    this@MainActivity,
                    object : Callback<Credentials, AuthenticationException> {
                        // Called when there is an authentication failure
                        override fun onFailure(exception: AuthenticationException) {
                            Timber.e("Exception : $exception")
                        }

                        // Called when authentication completed successfully
                        override fun onSuccess(credentials: Credentials) {
                            val accessToken = credentials.accessToken
                            Timber.d("Token : $accessToken")
                        }
                    },
                )
        }
    }
}
