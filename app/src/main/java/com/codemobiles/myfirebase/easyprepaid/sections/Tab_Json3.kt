package com.codemobiles.myfirebase.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codemobiles.myfirebase.easyprepaid.R
import com.codemobiles.myfirebase.test.beans.Youtube
import com.codemobiles.myfirebase.test.beans.YoutubeBean
import com.thefinestartist.ytpa.utils.YouTubeApp
import kotlinx.android.synthetic.main.fragment_json1.view.*
import kotlinx.android.synthetic.main.kfc_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab_Json3 : Fragment() {
    val adapter = CustomAdapter(ArrayList<Youtube>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val _view = inflater.inflate(R.layout.fragment_json2, container, false)
//        val mRcyclerView = v.findViewById<RecyclerView>(R.id.recycleView)
//        mRcyclerView.layoutManager = LinearLayoutManager(activity)
//        mRcyclerView.adapter = adapter

        _view.recycleView.let {
                mRecyclerView ->

            // important
            //แบบลิสต์แนวตั้ง
            mRecyclerView.layoutManager = LinearLayoutManager(context)
            // แบบลิสต์แนวนอน
//            mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
            // ลิสต์แบบแบ่งสองช่อง
//            mRecyclerView.layoutManager = GridLayoutManager(context, 2)



            mRecyclerView.adapter = adapter

            // it คือตัวแปรที่มัรสร้างไว้ให้อยู่แล้วใช้ได้เลย
//            it.layoutManager = LinearLayoutManager(context)
//            it.adapter = adapter
        }


        feed()
        return _view
    }

    private fun feed() {
        val httpClient = HttpClient.create()
        val call = httpClient.feed("foods")
        call.enqueue(object : Callback<YoutubeBean> {
            override fun onFailure(call: Call<YoutubeBean>, t: Throwable) {

            }

            override fun onResponse(call: Call<YoutubeBean>, response: Response<YoutubeBean>) {
                Log.i("mmm",response.body()!!.youtubes.toString())
                adapter.mDataArray.addAll(response.body()!!.youtubes)
                adapter.notifyDataSetChanged()
            }

        })

//        val httpClient = HttpClient.create()
//        val call = httpClient.feedType()
//
//        Log.d("network_url_retrofit", call.request().url().toString())
//        call.enqueue(object : Callback<List<JsonTest>>{
//            override fun onFailure(call: Call<List<JsonTest>>, t: Throwable) {
//                Log.d("aaa", t.message.toString())
//            }
//
//            override fun onResponse(call: Call<List<JsonTest>>, response: Response<List<JsonTest>>) {
//                Log.d("aaa", response.body().toString())
//            }
//
//        })

    }

    inner class CustomAdapter(val mDataArray:ArrayList<Youtube>) : RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, index: Int): CustomViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.kfc_list, parent, false)
            return CustomViewHolder(layout)
        }

        override fun getItemCount(): Int {
            return mDataArray.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, index: Int) {
            val item = mDataArray[index]
            holder.title.text = item.title
            holder.subtitle.text = item.subtitle
            holder.pay.text = item.subtitle
            Glide.with(activity!!).load(item.youtube_image).into(holder.youtube_image)


            //เซ็ตแท็กเพื่อใช้อ้างอิงอินเด็ก เพราะแอนดรอยไม่มีอินเด็กให้ใช้เพื่อเข้าถึงแต่ละโรล
            holder.youtube_image.setTag(R.id.image, item.id)
        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val subtitle = itemView.detail
        val pay = itemView.pay
        val youtube_image = itemView.image

        init {
//            itemView.setOnClickListener {
//                //คลิ๊กได้ในแถวตรงไหนก็ได้
//            }

            youtube_image.setOnClickListener {
                var id = it.getTag(R.id.image) as String
                Log.d("pimonwan_youtube_id", id )

                YouTubeApp.startVideo(it.context, id)
            }
        }
    }
}
