package com.alliebe.mastersejin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alliebe.mastersejin.com.alliebe.mastersejin.ViewPagerAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapter by lazy{ViewPagerAdapter(supportFragmentManager)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference()
        myRef.child("message").setValue("Hello, World!")
        myRef.child("sejin").setValue("Hello, World!")

        viewpager_mainActivity.adapter = MainActivity@adapter

        recommend_btn.setOnClickListener {
            viewpager_mainActivity.setCurrentItem(0, false)
        }
        lookaround_btn.setOnClickListener {
            viewpager_mainActivity.setCurrentItem(1, false)
        }
        question_btn.setOnClickListener {
            viewpager_mainActivity.setCurrentItem(2, false)
        }


    }
}