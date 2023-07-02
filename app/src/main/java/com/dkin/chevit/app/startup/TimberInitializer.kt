package com.dkin.chevit.app.startup

import android.content.Context
import androidx.startup.Initializer
import com.dkin.chevit.app.BuildConfig
import timber.log.Timber

internal class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val notLoggingTree = object : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            }
        }
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else notLoggingTree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }
}
