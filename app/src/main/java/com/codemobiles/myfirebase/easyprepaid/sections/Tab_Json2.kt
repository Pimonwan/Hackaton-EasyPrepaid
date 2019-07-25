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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_json1.view.*
import kotlinx.android.synthetic.main.kfc_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab_Json2 : Fragment() {
    val adapter = CustomAdapter(ArrayList<Youtube>())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val _view = inflater.inflate(com.codemobiles.myfirebase.easyprepaid.R.layout.fragment_json2, container, false)

        _view.recycleView.let {
                mRecyclerView ->

            mRecyclerView.layoutManager = LinearLayoutManager(context)
            mRecyclerView.adapter = adapter }
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
    }

    inner class CustomAdapter(val mDataArray:ArrayList<Youtube>) : RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, index: Int): CustomViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(com.codemobiles.myfirebase.easyprepaid.R.layout.kfc_list, parent, false)

            //data
            val dialog = BottomSheetDialog(context!!)
            val view = layoutInflater.inflate(R.layout.popup_layout,null)



            return CustomViewHolder(layout,dialog,view)
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
            holder.youtube_image.setTag(com.codemobiles.myfirebase.easyprepaid.R.id.image, item.id)
        }
    }

    class CustomViewHolder(itemView: View,dialog: BottomSheetDialog,view: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val subtitle = itemView.detail
        val pay = itemView.pay
        val youtube_image = itemView.image

        init {
            itemView.setOnClickListener {
                dialog.setContentView(view)
                dialog.show()

            }




        }
    }
}
