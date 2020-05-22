package dev.damodaran.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dev.damodaran.planets.R
import dev.damodaran.planets.databinding.PlanetDetailBinding

const val SUMMARY = "summary"
const val IMAGE = "image"

class DetailFragment : Fragment(R.layout.planet_detail) {


    private lateinit var binding: PlanetDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PlanetDetailBinding.bind(view)

    }
    override fun onStart() {
        super.onStart()
        val thumbnail =  arguments?.getString(IMAGE)
        val summary = arguments?.getString(SUMMARY)

        updateView(summary,thumbnail)
    }

    fun updateView(summary:String?, thumbnail:String?){
        binding.apply {
            description = summary
            Glide.with(this@DetailFragment)
                .load(thumbnail)
                .centerCrop()
                .into(image)
        }
    }
}
