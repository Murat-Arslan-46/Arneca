package com.marslan.myfirstapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.marslan.myfirstapp.databinding.DeviceItemLayoutBinding

class PhoneAdapter (private var phoneList: List<DataModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DeviceItemLayoutBinding = DeviceItemLayoutBinding.inflate(inflater,parent,false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount() = phoneList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhoneViewHolder).bind(phoneList[position])
    }

    inner class PhoneViewHolder(private val binding: DeviceItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(phone: DataModel) {
            binding.apply {
                deviceIcon.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, phone.deviceId))
                deviceName.text = phone.deviceName
                deviceModel.text = phone.device_model
                deviceOs.text = phone.device_os
            }
        }

    }
}