package com.marslan.myfirstapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.marslan.myfirstapp.databinding.DeviceItemLayoutBinding

class DeviceAdapter (var deviceList: List<DataModel>, private val clickListener: (DataModel) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DeviceItemLayoutBinding.inflate(inflater,parent,false)
        return DeviceViewHolder(binding)
    }

    override fun getItemCount() = deviceList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DeviceViewHolder).bind(deviceList[position],clickListener)
    }

    inner class DeviceViewHolder(private val binding: DeviceItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(device: DataModel, clickListener: (DataModel) -> Unit) {
            binding.apply {
                deviceIcon.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,device.iconID))
                deviceName.text = device.deviceName
                deviceModel.text = device.deviceModel
                deviceOs.text = device.deviceOS
                root.setOnClickListener { clickListener(device) }
            }
        }

    }
}