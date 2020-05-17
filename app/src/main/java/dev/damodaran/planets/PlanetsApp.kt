package dev.damodaran.planets

import android.app.Application
import timber.log.Timber

class PlanetsApp:Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }
}