package com.marslan.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.marslan.myfirstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = arrayListOf(
            DataModel(R.drawable.icon2, "HUAWEI", "SNE-LX1", "android"),
            DataModel(R.drawable.icon, "CASPER", "SMG-HJ1", "android"),
            DataModel(R.drawable.icon, "HUAWEI", "FH-LT2", "android"),
            DataModel(R.drawable.icon2, "HUAWEI", "REA-SDS", "android"),
            DataModel(R.drawable.icon2, "APPLE", "RTX-NVD", "IOS"),
        )
        adapter = PhoneAdapter(list)
        Log.d("test", list[4].deviceName)
        binding.rvList.adapter = adapter
    }
}