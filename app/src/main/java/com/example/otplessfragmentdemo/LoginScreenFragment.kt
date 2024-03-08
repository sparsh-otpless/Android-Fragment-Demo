package com.example.otplessfragmentdemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.otpless.dto.OtplessResponse
import com.otpless.main.OtplessManager
import com.otpless.main.OtplessView
import org.json.JSONException
import org.json.JSONObject


class LoginScreenFragment : Fragment(R.layout.otpless_fragment) {
    var otplessView: OtplessView? = null
    private lateinit var tvToken: TextView
    private lateinit var tvAllDetails: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStartOtpless = view.findViewById<Button>(R.id.btnStartOtpless)
        tvToken = view.findViewById<TextView>(R.id.tvToken)
        tvAllDetails = view.findViewById(R.id.tvAllDetails)

        btnStartOtpless.setOnClickListener {
            // Initialise OtplessView
            otplessView = OtplessManager.getInstance().getOtplessView(activity)
            val extras = JSONObject()
            try {
                extras.put("method", "get")
                val params = JSONObject()
                params.put("cid", "I9HXYP33C1K9Z61ZIF0MI1PY4VZOFX6Q")
                extras.put("params", params)
            } catch (e: JSONException) {
                throw RuntimeException(e)
            }

            otplessView?.setCallback(this::onOtplessCallback, extras, true)
            otplessView?.showOtplessLoginPage(extras, this::onOtplessCallback)
        }
    }


    private fun onOtplessCallback(response: OtplessResponse) {
        if (response.errorMessage != null) {
// todo error handing
        } else {
            val token = response.data.optString("token")
// todo token verification with api
            tvToken.text = "Token - $token"
            tvAllDetails.text = "All details - ${response.data}"
        }
    }

}