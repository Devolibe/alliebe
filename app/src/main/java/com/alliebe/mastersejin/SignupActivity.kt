package com.alliebe.mastersejin

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var button_date: EditText? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth

        // get the references from layout file
        textview_date = this.hint_birth
        button_date = this.et_dateOfBirth_signup

        textview_date!!.text = "YYYY/MM/DD"

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@SignupActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })


        ibtn_signup.setOnClickListener {
            performRegister()
        }
    }

    private fun updateDateInView() {
        val myFormat = "yyyy/MM/dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.KOREA)
        textview_date!!.text = sdf.format(cal.getTime())
    }

    private fun performRegister() {
        val email = et_email_signup.text.toString()
        val password = et_password_signup.text.toString()
        val nickname = et_nickname_signup.text.toString()
        val dateOfBirth = textview_date!!.text.toString()
        val sex: String

        // 성별 선택 여부 검사
        var sexId: Int = rg_sex_signup.checkedRadioButtonId

        if (sexId != -1) { // If any radio button checked from radio group
            // Get the instance of radio button using id
            val radio: RadioButton = findViewById(rg_sex_signup.checkedRadioButtonId)
            sex = radio.text.toString()
        } else {
            // If no radio button checked in this radio group
            Toast.makeText(
                applicationContext, "성별을 선택해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        // 이메일과 비밀번호 검사
        if (email.isEmpty()) {

            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (nickname.isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (dateOfBirth.isEmpty()) {
            Toast.makeText(this, "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }




        Log.d("SignupActivity", "Email is: " + email)
        Log.d("SignupActivity", "Password: $password ")
        try {
            // Firebase Authentication to create a user with email and password

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (!it.isSuccessful) {

                        return@addOnCompleteListener
                    }
                    // else if successful
                    Log.d(
                        "SignupActivity",
                        "Successfully created user with uid: ${it.result?.user?.uid}"
                    )
                    val user = auth.currentUser

                    saveUserToFirebaseDatabase()
                    // updateUI(user)
                }
                .addOnFailureListener(this) {

                    Log.d("SignupActivity", "Failed to create user: ${it.message}")
                    var errorMsg : String ?= it.message
                    if(errorMsg.equals("Password should be at least 6 characters")) {
                        Toast.makeText(this, "6자 이상의 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
                    }
                    else if(errorMsg.equals("The email address is already in use by another account.")) {
                        Toast.makeText(this, "이미 사용 중인 이메일입니다.",Toast.LENGTH_SHORT).show()
                    }
                }
        } catch(e:FirebaseAuthUserCollisionException) {
            Toast.makeText(this, "이미 사용중인 이메일입니다.",Toast.LENGTH_SHORT).show()
        } catch(e: FirebaseAuthWeakPasswordException) {
            Toast.makeText(this, "6자 이상의 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Log.d("SignupActivity", "Failed to create user")
        }
    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val radio: RadioButton = findViewById(rg_sex_signup.checkedRadioButtonId)
        val sex = radio.text.toString()
        val user = User(
            uid,
            et_nickname_signup.text.toString(),
            textview_date!!.text.toString(),
            sex
        )

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("SignupActivity", "Finally we saved the user to Firebase Database")
            }
    }
}

class User(val uid: String, val nickname: String, val dateOfBirth: String, val sex: String)