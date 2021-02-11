package com.surepass.exampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.surepass.captureandroidsdk.ui.VerificationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CONSOLE = "CONSOLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startFullVerification()
        }

    }

    fun startFullVerification(){

        //token is needed when using full verification
        val token = "TOKEN"
        //Set Enviroment as TEST or PROD
        val env = "TEST"

        val intent = Intent(this, VerificationActivity::class.java)
        intent.putExtra("token",token)
        intent.putExtra("env",env)

        //ID Options ["aadhaar","pancard","license","passport","voterid"]
        intent.putExtra("pancard",true)

        startActivityForResult(
            intent,
            10000
        )
    }

    private fun setResponseOnTextView(resp : String){
        responseView.text = resp
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10000 -> {
                val response = data?.getStringExtra("response");
                Log.d(CONSOLE, "response " + response)
                response?.let { setResponseOnTextView(it) }
            }
        }
    }
}