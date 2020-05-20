package dev.damodaran.planets.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.damodaran.planets.api.Planet
import dev.damodaran.planets.databinding.ItemPlanetBinding

class PlanetsAdapter(private val list:List<Planet>, listener:OnPlanetSelectedListener) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {
    var planetClickListener : OnPlanetSelectedListener = listener

    interface OnPlanetSelectedListener {
        fun onPlanetSelected(summary: String,thumbnail:String)
    }

    class ViewHolder(val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(planet:Planet){
            binding.apply {
                title = planet.name.toUpperCase()
                description = planet.description
                Glide.with(binding.root.context)
                    .load(planet.image)
                    .centerCrop()
                    .into(thumbnail)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding=  ItemPlanetBinding.inflate(layoutInflater,
            parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val planet = list[position]
        holder.bind(planet)
        holder.binding.root.setOnClickListener {
            planetClickListener.onPlanetSelected(planet.description,
                planet.image)
        }
    }
}