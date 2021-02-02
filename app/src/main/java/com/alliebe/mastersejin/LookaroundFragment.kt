package com.alliebe.mastersejin.com.alliebe.mastersejin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alliebe.mastersejin.R

class LookaroundFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_lookaround,container,false)
        return view
    }
}