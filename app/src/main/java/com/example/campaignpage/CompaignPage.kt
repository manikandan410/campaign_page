package com.example.campaignpage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.campaignpage.Adapter.MyAdapter
import com.google.android.material.tabs.TabLayout

class CompaignPage : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

   // @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compaign_page)

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)

     
        tabLayout.addTab(tabLayout.newTab().setText("person1"))
        tabLayout.addTab(tabLayout.newTab().setText("person2"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(this,supportFragmentManager,
        tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}