package com.example.otplessfragmentdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otpless.main.OtplessManager
import com.otpless.main.OtplessView

class MainActivity : AppCompatActivity() {
    var otplessView: OtplessView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        otplessView = OtplessManager.getInstance().getOtplessView(this)

        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            LoginScreenFragment()
        ).commit()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (otplessView != null) {
            otplessView?.verifyIntent(intent);
        }
    }
}