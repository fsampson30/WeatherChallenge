package com.sampson.weatherchallenge.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.model.PopulateCityList

class CityAdapter(
    val context: Context
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    var cityList = PopulateCityList.populateList()

    class CityViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCityName = itemView.findViewById<TextView>(R.id.txtCityNameListView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.txtCityName.text = cityList[position]
    }

    override fun getItemCount() = cityList.size
}