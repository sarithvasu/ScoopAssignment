package com.scoop.scoopassignment.internal

import android.app.Application
import android.content.Context


class ScoopApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        scoopContext = applicationContext

    }

    companion object {
        var scoopContext: Context? = null
    }
}