package com.alliebe.mastersejin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.LookaroundFragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.SearchFragment
import com.alliebe.mastersejin.com.alliebe.mastersejin.ViewPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.main_drawer_header.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentLookaround by lazy { LookaroundFragment() }
    private val fragmentSearch by lazy { SearchFragment() }
    private val fragmentMypage by lazy { MyPageFragment() }

    private val adapter by lazy { ViewPagerAdapter(supportFragmentManager) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference()
        myRef.child("message").setValue("Hello, World!")
        myRef.child("sejin").setValue("Hello, World!")

        val fab = findViewById<FloatingActionButton>(R.id.edit_fab)
        initNavigationBar()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_hamburger)
        mDrawerToggle = ActionBarDrawerToggle(this, dl_main, toolbar, R.string.open, R.string.close)
        mDrawerToggle!!.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nv_main)
        navigationView.setNavigationItemSelectedListener(this)
        val NavHeader = navigationView.getHeaderView(0)

        Glide.with(this)
            .load("https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9956E8395DE4E4AC07")
            .override(70,70)
            .circleCrop()
            .into(NavHeader.iv_profile)

        NavHeader.iv_close.setOnClickListener { dl_main.closeDrawers() }

        /* 툴바 이미지 변경
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_logo)
         */
    }

    // main_menu 메뉴 레이아웃을 toolbar 메뉴 버튼으로 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initNavigationBar() {
        nv_bottom.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        }
        dl_main.closeDrawers()
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(dl_main.isDrawerOpen(GravityCompat.START)){
            dl_main.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked",Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }
}