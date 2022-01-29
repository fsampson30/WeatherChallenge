package com.sampson.weatherchallenge.controller

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampson.weatherchallenge.DAO.EntityCity
import com.sampson.weatherchallenge.DAO.EntityCityPartials
import com.sampson.weatherchallenge.view.CityDetailsActivity
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.model.PopulateCityList

class CityDetailsAdapter(
    private val context: Context
) : RecyclerView.Adapter<CityDetailsAdapter.CityViewHolder>() {

    private var partials = mutableListOf<EntityCityPartials>()

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMainWeather: TextView = itemView.findViewById(R.id.txtCityDetaisMainWeatherPartial)
        val txtTemperature: TextView = itemView.findViewById(R.id.txtCityDetaisTemperaturePartial)
        val txtTimeConsult: TextView = itemView.findViewById(R.id.txtCityDetaisTimePartial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_list_item_partials, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.txtMainWeather.text = partials[position].cityMainWeather
        holder.txtTemperature.text = partials[position].cityTemperature
        holder.txtTimeConsult.text = partials[position].consultTime
    }

    override fun getItemCount() = partials.size

    fun submitList(partials: MutableList<EntityCityPartials>) {
        this.partials = partials
        notifyDataSetChanged()
    }
}