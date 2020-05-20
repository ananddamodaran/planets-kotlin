package dev.damodaran.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dev.damodaran.planets.databinding.PlanetDetailBinding

const val SUMMARY = "summary"
const val IMAGE = "image"

class DetailFragment : Fragment() {

    lateinit var  binding: PlanetDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlanetDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val thumbnail =  arguments?.getString(IMAGE)
        binding.apply {
            description = arguments?.getString(SUMMARY)
            Glide.with(this@DetailFragment)
                .load(thumbnail)
                .centerCrop()
                .into(image)
        }
    }

}
