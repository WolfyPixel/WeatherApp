package com.company.restapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.restapplication.databinding.ItemWeatherBinding
import com.company.restapplication.db.models.Weather

class WeatherAdapter(
    val items: ArrayList<Weather>,
    val listener: ItemClickListener
) : RecyclerView.Adapter<WeatherAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.textName.text = item.city_name

        holder.binding.root.setOnClickListener {
            listener.onClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    fun interface ItemClickListener {
        fun onClick(item: Weather, position: Int)
    }
}