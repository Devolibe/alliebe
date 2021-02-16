package com.alliebe.mastersejin

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {
    private var pendingEmail: String = ""
    private var emailLink: String = ""
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        auth = Firebase.auth
        val intent = intent
        val emailLink = intent.data.toString()

        signup_button.setOnClickListener {
            onSendLinkClicked()
        }

        if(auth.isSignInWithEmailLink(emailLink)){
            val email = email_signup_editText.getText().toString().trim()

            auth.signInWithEmailLink(email, emailLink).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d("TAG","Success")
                    val result = task.result
                }
                else {
                    Log.e("TAG","Error",task.exception)
                }
            }
        }

    }


    private fun intentHasEmailLink(intent: Intent?): Boolean{
        if(intent!=null && intent.data!=null){
            val intentData = intent.data.toString()
            if(auth.isSignInWithEmailLink(intentData)){
                return true
            }
        }
        return false
    }

    private fun sendSignInLink(email: String){
        val settings = actionCodeSettings{
            setAndroidPackageName(
                packageName,
                false, null)
            handleCodeInApp = true
            url="https://alliebe.page.link"
        }
        auth.sendSignInLinkToEmail(email, settings).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("TAG","Link sent")

            }
            else {
                val e = task.exception
                Log.w("TAG","Could not send link",e)

            }
        }
    }



    private fun onSendLinkClicked(){
        val email = email_signup_editText.getText().toString().trim()
        sendSignInLink(email)
    }



}

