package dev.damodaran.planets.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.damodaran.planets.R
import dev.damodaran.planets.api.Planet
import dev.damodaran.planets.databinding.ItemPlanetBinding

class PlanetsAdapter(private val list:List<Planet>, listener: PlanetListFragment.OnPlanetSelectedListener) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {
    private var planetClickListener : PlanetListFragment.OnPlanetSelectedListener = listener

    class ViewHolder(val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(planet:Planet){
            binding.apply {
                title = planet.name.toUpperCase()
                description = planet.description
                thumbnailImage = planet.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_planet, parent, false
            )
        )

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