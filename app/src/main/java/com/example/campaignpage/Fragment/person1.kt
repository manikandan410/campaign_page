package com.example.campaignpage.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.campaignpage.R
import com.example.campaignpage.Video


class person1 : Fragment() {
    private lateinit var person1: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_person1, container, false)

        person1= root.findViewById(R.id.person1)
        person1.setOnClickListener{
            startActivity(Intent( this@person1.requireContext(), Video::class.java))

        }

        return root


    }


}