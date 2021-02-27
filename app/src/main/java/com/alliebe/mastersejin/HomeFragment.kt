package com.alliebe.mastersejin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alliebe.mastersejin.com.alliebe.mastersejin.ViewPagerAdapter
import com.alliebe.mastersejin.factory.BannerItem
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewPagerAdapter: GuideViewPagerAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        viewModel.setBannerItems(
            listOf(
                BannerItem(R.drawable.img_guide_sample),
                BannerItem(R.drawable.img_guide_sample2),
                BannerItem(R.drawable.img_guide_sample3)
            )
        )

        initViewPager2()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(this, Observer {
            bannerItemList -> viewPagerAdapter.submitList(bannerItemList)
        })
    }

    private fun initViewPager2() {
        vp_guide.apply {
            viewPagerAdapter = GuideViewPagerAdapter()
            adapter = viewPagerAdapter
        }
    }


}