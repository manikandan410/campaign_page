package com.example.campaignpage.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campaignpage.Adapter.AdapterVideo
import com.example.campaignpage.AddVideoActivity
import com.example.campaignpage.ModelVideo
import com.example.campaignpage.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class person1 : Fragment() {


    private lateinit var addVideoFab : FloatingActionButton

    //arraylist for video list
    private lateinit var videoArrayList: ArrayList<ModelVideo>
    //adapter
    private lateinit var adapterVideo: AdapterVideo

    private lateinit var videosRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_person1, container, false)


        //function call to load video from firebase
        loadVideosFromFirebase()

        addVideoFab = root.findViewById(R.id.addVideoFab)
        addVideoFab.setOnClickListener {
            startActivity(Intent(this@person1.requireContext(), AddVideoActivity::class.java))

        }

          return root


          }


        private fun loadVideosFromFirebase() {
            //init arraylist before adding data into it
            videoArrayList = ArrayList()

            //reference of firebase db
            val ref = FirebaseDatabase.getInstance().getReference("Videos")
            ref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    //clear list before adding data into it
                    videoArrayList.clear()
                    for (ds in snapshot.children) {
                        //get data as model
                        val modelVideo = ds.getValue(ModelVideo::class.java)
                        //add to array list
                        videoArrayList.add(modelVideo!!)
                    }
                    //setup adapter
                    adapterVideo = AdapterVideo(this@person1.requireContext(), videoArrayList)
                    //set adapter to recyclerview
                    videosRv = view!!.findViewById(R.id.videosRv) as RecyclerView
                    videosRv.adapter = adapterVideo

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        }



}