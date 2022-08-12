package com.example.navigationdrawerapp

import android.telecom.Call
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://dummyjson.com/users

const val BASE_URL ="https://dummyjson.com/"
const val API_KEY ="users"
interface DataInterface {
    @GET("users")
  fun getData(): retrofit2.Call<AllUsers>

}

object Data{
val dataInstance : DataInterface

    init{
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        dataInstance=retrofit.create(DataInterface::class.java)

}
}