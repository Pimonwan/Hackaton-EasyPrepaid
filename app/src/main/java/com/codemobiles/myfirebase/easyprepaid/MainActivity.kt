package com.codemobiles.myfirebase.easyprepaid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // button
        topage2.setOnClickListener{
            startActivity(Intent(applicationContext, Menu::class.java))
        }
    }
}
