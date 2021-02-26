package com.alliebe.mastersejin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alliebe.mastersejin.factory.BannerItem

class HomeFragmentViewModel : ViewModel() {
    private val _bannerItemList : MutableLiveData<List<BannerItem>> = MutableLiveData()

    val bannerItemList : LiveData<List<BannerItem>>
    get() = _bannerItemList

    fun setBannerItems(list: List<BannerItem>){
        _bannerItemList.value = list
    }
}