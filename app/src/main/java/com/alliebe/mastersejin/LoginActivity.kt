package com.alliebe.mastersejin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signin.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        btn_login.setOnClickListener{
            val email = et_id.text.toString()
            val password = et_password.text.toString()

            Log.d("Login", "Attemt login with email/pw: $email/***")
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d("Login", "Successfully logged in with uid: ${it.result?.user?.uid}")
                val user =  auth.currentUser
                updateUI(user)
            }
            .addOnFailureListener {
                Log.d("Login", "Failed to log in user: ${it.message}")
                Toast.makeText(this,"Failed to log in user: ${it.message}", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        // 가입하기 버튼 클릭
        txt_signIn.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    // [END on_start_check_user]
}