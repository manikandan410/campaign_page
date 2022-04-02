package com.example.campaignpage.Adapter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.campaignpage.ModelVideo2
import com.example.campaignpage.R
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class AdapterVideo2(
    private var context2: Context,
    private var videoArrayList2 : ArrayList<ModelVideo2>
) : RecyclerView.Adapter<AdapterVideo2.HolderVideo>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVideo2.HolderVideo {
        //inflate layout row_video.xml
        val view = LayoutInflater.from(context2).inflate(R.layout.row_video2, parent, false)
        return AdapterVideo2.HolderVideo(view)
    }

    override fun onBindViewHolder(holder: AdapterVideo2.HolderVideo, position: Int) {
        /*-----get data, set data, handle clicks etc-----*/

        //get data
        val modelVideo2 = videoArrayList2!![position]

        //get speific data
        val id2: String? = modelVideo2.id2
        val title2: String? = modelVideo2.title2
        val timestamp2: String? = modelVideo2.timestamp2
        val videoUri2: String? = modelVideo2.videoUri2


        //format date e.g. 16/03/2022 11:38am
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp2!!.toLong()
        val formattedDateTime = android.text.format.DateFormat.format("dd/MM/yyyy K:mm a", calendar).toString()

        //set data
        holder.titleTv2.text = title2
        holder.timeTv2.text = formattedDateTime
        setVideoUrl(modelVideo2, holder)



    }

    private fun setVideoUrl(modelVideo: ModelVideo2, holder: HolderVideo) {
        //show progress
        holder.progressBar2.visibility = View.VISIBLE

        //get video uri
        val videoUrl: String? = modelVideo.videoUri2

        //MediaController for play/pause/time etc
        val mediaController = MediaController(context2)
        mediaController.setAnchorView(holder.videoView2)
        val videoUri = Uri.parse(videoUrl)

        holder.videoView2.setMediaController(mediaController)
        holder.videoView2.setVideoURI(videoUri)
        holder.videoView2.requestFocus()

        holder.videoView2.setOnPreparedListener {mediaPlayer ->
            //video is prepared to play
            mediaPlayer.start()
        }
        holder.videoView2.setOnInfoListener(MediaPlayer.OnInfoListener{ mp, what, extra->
            //check if buffering/rendering etc
            when(what){
                MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START ->{
                    //rendering started
                    holder.progressBar2.visibility = View.VISIBLE
                    return@OnInfoListener true
                }
                MediaPlayer.MEDIA_INFO_BUFFERING_START ->{
                    //buffering started
                    holder.progressBar2.visibility = View.VISIBLE
                    return@OnInfoListener true
                }
                MediaPlayer.MEDIA_INFO_BUFFERING_END ->{
                    //buffering ended
                    holder.progressBar2.visibility = View.GONE
                    return@OnInfoListener true
                }
            }

            false
        })

        holder.videoView2.setOnCompletionListener {mediaPlayer ->
            //restart video when completed | loop video
            mediaPlayer.start()

        }


    }

    override fun getItemCount(): Int {
        return videoArrayList2!!.size //return size/length or the arraylist
    }


    class HolderVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //init UI Views
        var videoView2: VideoView = itemView.findViewById(R.id.videoView2)
        var titleTv2: TextView = itemView.findViewById(R.id.titleTv2)
        var timeTv2: TextView = itemView.findViewById(R.id.timeTv2)
        var progressBar2: ProgressBar = itemView.findViewById(R.id.progressBar2)


    }



}