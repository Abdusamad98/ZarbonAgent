package com.example.zarbondistributionagent.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.nabinbhandari.android.permissions.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance =  this
        TokenSaver.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    override fun attachBaseContext(base: Context) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

    companion object {
        lateinit var instance: App private set
    }
}