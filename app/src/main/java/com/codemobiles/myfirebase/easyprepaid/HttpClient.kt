package com.codemobiles.myfirebase.test

import com.codemobiles.myfirebase.test.beans.YoutubeBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://jsonplaceholder.typicode.com/users

interface HttpClient {
    @GET("/adhoc/youtubes/index_new.php/")
    fun feed(@Query("type") type:String): Call<YoutubeBean>

//    @GET("/posts")
//    fun feedType(): Call<List<JsonTest>>

    companion object{
        val BASE_URL = "http://codemobiles.com"
        //        val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun create() : HttpClient {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(HttpClient::class.java)
        }
    }
}