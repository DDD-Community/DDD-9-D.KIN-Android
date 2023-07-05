package com.dkin.chevit.app

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()
