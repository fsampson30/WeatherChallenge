package com.sampson.weatherchallenge.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.model.PopulateCountryList

class CountryAdapter(
    val context: Context
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    var countryList = PopulateCountryList.populateList()

    class CountryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCountryName = itemView.findViewById<TextView>(R.id.txtCountryNameListView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.txtCountryName.text = countryList[position]
    }

    override fun getItemCount() = countryList.size
}