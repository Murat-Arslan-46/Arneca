package com.marslan.myfirstapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.marslan.myfirstapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DeviceAdapter
    private var index: DataModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = DeviceAdapter(listOf()) { device: DataModel -> onClick(device)}
        binding.apply{
            rvList.adapter = adapter
            add.setOnClickListener {
                addDevice(
                    DataModel(
                        Random.nextInt(999),
                        R.drawable.icon,
                        "HUAWEI",
                        "SNE-LX1",
                        "android"
                    )
                )
            }
        }
        refresh()
    }

    fun refresh() {
        ClientApi.create().getDevice().enqueue( object : Callback<List<DataModel>>
        {
            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?)
            {
                if(!(response?.body() == null || response.body()?.size == 0))
                {
                    adapter.deviceList = response.body()!!
                    adapter.notifyDataSetChanged()
                    Log.d("test", response.body()?.get(0)?.deviceName.toString())
                }
                else if(response?.body()?.size == 0){
                    adapter.deviceList = listOf()
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?)
            {
                Log.e("test", t?.message.toString())
            }
        })
    }

    private fun addDevice(device: DataModel) {
        ClientApi.create().addDevice(device).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("test", "add complate")
                refresh()
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.e("test", t?.message.toString())
            }
        })
    }

    private fun deleteDevice(device: DataModel){
        ClientApi.create().removeDevice(device.id).enqueue( object : Callback<Void>{
            override fun onResponse(call: Call<Void>?, response: Response<Void>?)
            {
                Log.d("test", "delete completed")
                refresh()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?)
            {
                Log.e("test", t?.message.toString())
            }
        })
        binding.update.setOnClickListener {  }
        binding.delete.setOnClickListener {  }
    }

    private fun updateDevice(id: Int, device: DataModel){
        ClientApi.create().updateDevice(id,device).enqueue( object : Callback<Void>{
            override fun onResponse(call: Call<Void>?, response: Response<Void>?)
            {
                Log.d("test", "update completed")
                refresh()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?)
            {
                Log.e("test", t?.message.toString())
            }
        })
        binding.update.setOnClickListener {  }
        binding.delete.setOnClickListener {  }
    }

    private fun onClick(device: DataModel){
        index = device
        binding.apply{
            update.setOnClickListener {
                updateDevice(index!!.id, DataModel(
                    index!!.id,
                    index!!.iconID,
                    index!!.deviceName,
                    Random.nextInt(9999,99999).toString(),
                    index!!.deviceOS
                )
                )
            }
            delete.setOnClickListener {
                deleteDevice(index!!)
            }
        }
    }
}

