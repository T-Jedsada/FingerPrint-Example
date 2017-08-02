package com.jedsada.fingerprint.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.ajalt.reprint.core.AuthenticationResult
import com.github.ajalt.reprint.core.Reprint
import com.github.ajalt.reprint.rxjava2.RxReprint
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        startFingerprint()
    }

    override fun onStop() {
        super.onStop()
        Reprint.cancelAuthentication()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startFingerprint() {
        RxReprint.authenticate().subscribe {
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            when (it.status) {
                AuthenticationResult.Status.SUCCESS -> showSuccess(it.status)
                else -> showError(it.errorMessage)
            }
        }
    }

    private fun showSuccess(status: AuthenticationResult.Status?) {
        fingerprint_status.text = status?.name
        fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_success)
    }

    private fun showError(errorMessage: CharSequence?) {
        fingerprint_status.text = errorMessage.toString()
        fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_error)
    }
}
