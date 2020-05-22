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

class DetailFragment : Fragment() {

    private var binding: PlanetDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(inflater,
             R.layout.planet_detail,
             container,false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val thumbnail =  arguments?.getString(IMAGE)
        val summary = arguments?.getString(SUMMARY)
        updateView(summary,thumbnail)

    }
    fun updateView(summary: String?, thumbnail: String?) {
        binding?.apply {
            description = summary
            Glide.with(this@DetailFragment)
                .load(thumbnail)
                .centerCrop()
                .into(image)
        }
    }
}
