package dev.damodaran.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dev.damodaran.planets.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.planet_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return layoutInflater.inflate(R.layout.planet_detail,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val img = arguments?.getString("image")
        val summaryVal = arguments?.getString("summary")

        Glide.with(this)
            .load(img)
            .centerCrop()
            .into(image)

        summary.text = summaryVal
    }

}