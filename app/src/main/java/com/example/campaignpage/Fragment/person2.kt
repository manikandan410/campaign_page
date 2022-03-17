package com.example.campaignpage.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.campaignpage.R
import com.example.campaignpage.Video


class person2 : Fragment() {

   private lateinit var person2: FrameLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_person2, container, false)


       person2 = root.findViewById(R.id.person2)
        person2.setOnClickListener {
            startActivity(Intent(this@person2.requireContext(),Video::class.java))
        }


        return root

    }


}