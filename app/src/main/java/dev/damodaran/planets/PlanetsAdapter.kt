package dev.damodaran.planets

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber

class PlanetsAdapter(private val list:List<Planet>) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) , View.OnClickListener{
        val title: TextView = view.findViewById(R.id.title)
        val summary: TextView = view.findViewById(R.id.summary)
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
        override fun onClick(view: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=  LayoutInflater.from(parent.context).inflate(
            R.layout.item_planet,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context=holder.itemView.context
       val planet = list[position];
        holder.title.text = planet.name.toUpperCase()
        holder.summary.text = planet.description
        holder.itemView.setOnClickListener {
            val detail = Intent(context,Detail::class.java)
            detail.putExtra("summary", planet.description)
            detail.putExtra("image", planet.image)
            context.startActivity(detail)
        }

        Glide.with(context)
            .load(planet.image)
            .centerCrop()
            .into(holder.thumbnail)
    }
}