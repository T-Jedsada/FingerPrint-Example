package com.jedsada.fingerprint.example

import android.app.Application
import android.util.Log
import com.github.ajalt.reprint.core.Reprint

class MyApplication : Application() {

    private val TAG = MyApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Reprint.initialize(this, object : Reprint.Logger {
            override fun logException(throwable: Throwable?, message: String?) {
                throwable?.printStackTrace()
                Log.d(TAG, message)
            }

            override fun log(message: String?) {
                Log.d(TAG, message)
            }
        })
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}