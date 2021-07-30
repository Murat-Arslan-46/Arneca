package com.marslan.myfirstapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ClientApi {
    @GET("devices")
    fun getDevice() : Call<List<DataModel>>

    @POST("devices/")
    fun addDevice(@Body newDevice:DataModel) : Call<Void>

    @DELETE("devices/{id}")
    fun removeDevice(@Path("id") id : Int) : Call<Void>

    @PUT("devices/{id}")
    fun updateDevice(@Path("id") id : Int , @Body updateDevice:DataModel) : Call<Void>

    companion object {
        fun create() : ClientApi{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.40:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ClientApi::class.java)
        }
    }
}