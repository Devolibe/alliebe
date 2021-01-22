package com.alliebe.mastersejin

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signin.*


class SigninActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = Firebase.auth
        ibtn_signin.setOnClickListener {
            performRegister()



        }
    }
    private fun performRegister() {
        val email = et_email_signin.text.toString()
        val password = et_password_signin.text.toString()
        val nickname = et_nickname_signin.text.toString()
        val dateOfBirth = et_dateOfBirth_signin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(this,"이메일 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("SigninActivity", "Email is: " + email)
        Log.d("SigninActivity", "Password: $password ")

        // Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d("Signin", "Successfully created user with uid: ${it.result?.user?.uid}")
                val user = auth.currentUser
                updateUI(user)
            }
            .addOnFailureListener{
                Log.d("Signin", "Failed to create user: ${it.message}")
                Toast.makeText(this,"Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}