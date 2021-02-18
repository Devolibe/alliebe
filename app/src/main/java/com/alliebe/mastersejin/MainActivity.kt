package com.alliebe.mastersejin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.LookaroundFragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.SearchFragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_test.*


class MainActivity : AppCompatActivity() {

    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentLookaround by lazy { LookaroundFragment() }
    private val fragmentSearch by lazy { SearchFragment()}
    private val fragmentMypage by lazy { MyPageFragment()}

    private val adapter by lazy{ViewPagerAdapter(supportFragmentManager)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference()
        myRef.child("message").setValue("Hello, World!")
        myRef.child("sejin").setValue("Hello, World!")

        initNavigationBar()
    }

    private fun initNavigationBar() {
        nav_view.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                   R.id.navigation_home -> {
                       changeFragment(fragmentHome)
                   }
                    R.id.navigation_lookaround -> {
                        changeFragment((fragmentLookaround))
                    }
                    R.id.navigation_search -> {
                        changeFragment((fragmentSearch))
                    }
                    R.id.navigation_mypage -> {
                        changeFragment((fragmentMypage))
                    }
                }
                true
            }
            selectedItemId = R.id.navigation_home
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}