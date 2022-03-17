package com.example.campaignpage.Adapter


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.campaignpage.Fragment.person1
import com.example.campaignpage.Fragment.person2



internal class MyAdapter(var context: Context, fm: FragmentManager,var totalTabs: Int):
                                                    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
       return when(position){
           0 -> {
               person1()
           }
           1 -> {
               person2()
           }
           else -> getItem(position)
       }
    }
    override fun getCount(): Int {
        return totalTabs

    }


}