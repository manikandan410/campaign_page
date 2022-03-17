package com.example.campaignpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var click: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click = findViewById(R.id.click)
        click.setOnClickListener {
            startActivity(Intent(this@MainActivity, CompaignPage::class.java ))


        }
    }
}