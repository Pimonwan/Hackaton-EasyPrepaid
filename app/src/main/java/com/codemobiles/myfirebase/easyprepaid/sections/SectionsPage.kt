package com.codemobiles.myfirebase.test.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobiles.myfirebase.easyprepaid.R

import com.codemobiles.myfirebase.test.Tab_Json1
import com.codemobiles.myfirebase.test.Tab_Json2
import com.codemobiles.myfirebase.test.Tab_Json3

import kotlinx.android.synthetic.main.menu.view.*

class SectionsPage(private val context: Context, fm: FragmentManager, val intent: Intent): FragmentPagerAdapter(fm)  {
    private val PAGES: Int = 3
    private val TAB_TITLES = arrayOf("JSON1","JSON2","JSON3")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                Tab_Json1()
            }
            1 -> {
                Tab_Json2()
            }
            else -> {
                Tab_Json3()
            }
        }
    }

    override fun getCount(): Int {
        return PAGES
    }

    //custom menu tabs
    fun getTabView(position: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null).apply {
            title.text = TAB_TITLES[position]
        }
    }

}