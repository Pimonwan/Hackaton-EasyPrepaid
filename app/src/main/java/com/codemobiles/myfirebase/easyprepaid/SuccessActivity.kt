package com.codemobiles.myfirebase.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.codemobiles.myfirebase.easyprepaid.R
import com.codemobiles.myfirebase.test.ui.SectionsPage
import com.google.android.material.tabs.TabLayout


class SuccessActivity : AppCompatActivity() {

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
    }
}