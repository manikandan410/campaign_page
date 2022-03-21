package com.example.campaignpage.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campaignpage.*
import com.example.campaignpage.Adapter.AdapterVideo
import com.example.campaignpage.Adapter.AdapterVideo2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class person2 : Fragment() {

    private lateinit var addVideoFab2 : FloatingActionButton

    //arraylist for video list
    private lateinit var videoArrayList2: ArrayList<ModelVideo2>
    //adapter
    private lateinit var adapterVideo2: AdapterVideo2

    private lateinit var videosRv2: RecyclerView





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_person2, container, false)

        //function call to load video from firebase
        loadVideosFromFirebase()


       addVideoFab2 = root.findViewById(R.id.addVideoFab2)
        addVideoFab2.setOnClickListener {
            startActivity(Intent(this@person2.requireContext(),AddVideoActivity2::class.java))
        }


        return root

    }



    private fun loadVideosFromFirebase() {
        //init arraylist before adding data into it
        videoArrayList2 = ArrayList()

        //reference of firebase db
        val ref = FirebaseDatabase.getInstance().getReference("Mani")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //clear list before adding data into it
                videoArrayList2.clear()
                for (ds in snapshot.children) {
                    //get data as model
                    val modelVideo2 = ds.getValue(ModelVideo2::class.java)
                    //add to array list
                    videoArrayList2.add(modelVideo2!!)
                }
                //setup adapter
                adapterVideo2 = AdapterVideo2(this@person2.requireContext(), videoArrayList2)
                //set adapter to recyclerview
                videosRv2 = view!!.findViewById(R.id.videosRv2) as RecyclerView
                videosRv2.adapter = adapterVideo2

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

      }





}