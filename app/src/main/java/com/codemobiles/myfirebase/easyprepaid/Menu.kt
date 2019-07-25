package com.codemobiles.myfirebase.easyprepaid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.codemobiles.myfirebase.test.ui.SectionsPage
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.menu.*


class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val sectionsPagerAdapter = SectionsPage(this, supportFragmentManager, intent)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // custom tabs
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            tab!!.customView = sectionsPagerAdapter.getTabView(i)
        }

        // button
        cart_ic_image.setOnClickListener{
            startActivity(Intent(applicationContext, Menu::class.java))
        }


    }
}