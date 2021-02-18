package com.alliebe.mastersejin.com.alliebe.mastersejin

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alliebe.mastersejin.HomeFragment
import com.alliebe.mastersejin.MyPageFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> LookaroundFragment()
            2 -> SearchFragment()
            else -> MyPageFragment()
        }
    }

    override fun getCount() = 4

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}