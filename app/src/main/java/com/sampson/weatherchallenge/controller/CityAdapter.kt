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
import com.sampson.weatherchallenge.view.CityDetailsActivity
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.model.PopulateCityList

class CityAdapter(
    private val context: Context
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cityList = mutableListOf<EntityCity>()

    class CityViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCityName: TextView = itemView.findViewById(R.id.txtCurrentLocationMainActivity)
        val btnCityDetails: Button = itemView.findViewById(R.id.btnCurrentLocationMainActivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.txtCityName.text = cityList[position].cityName
        val cityId = cityList[position].cityCode

        holder.btnCityDetails.setOnClickListener {
            val intent = Intent(context, CityDetailsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("cityId", cityId)
            context.startActivity(intent)

        }
    }

    override fun getItemCount() = cityList.size

    fun submitList(cities: MutableList<EntityCity>) {
        this.cityList = cities
        notifyDataSetChanged()
    }

    fun getCityAtPosition(position: Int): EntityCity{
        return cityList[position]
    }
}