package com.example.campaignpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.example.campaignpage.Adapter.AdapterVideo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Video : AppCompatActivity() {

    private lateinit var addVideoFab : FloatingActionButton

    //arraylist for video list
    private lateinit var videoArrayList: ArrayList<ModelVideo>
    //adapter
    private lateinit var adapterVideo: AdapterVideo

    private lateinit var videosRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        //function call to load video from firebase
        loadVideosFromFirebase()

         addVideoFab = findViewById(R.id.addVideoFab)
         addVideoFab?.setOnClickListener {
             startActivity(Intent(this@Video, AddVideoActivity::class.java))
         }


    }

    private fun loadVideosFromFirebase() {
        //init arraylist before adding data into it
        videoArrayList = ArrayList()

        //reference of firebase db
        val ref = FirebaseDatabase.getInstance().getReference("Videos")
        ref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                //clear list before adding data into it
                videoArrayList.clear()
                for (ds in snapshot.children){
                    //get data as model
                    val modelVideo = ds.getValue(ModelVideo::class.java)
                    //add to array list
                    videoArrayList.add(modelVideo!!)
                }
                //setup adapter
                adapterVideo = AdapterVideo(this@Video, videoArrayList)
                //set adapter to recyclerview
                videosRv = findViewById(R.id.videosRv)
                videosRv.adapter = adapterVideo

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}