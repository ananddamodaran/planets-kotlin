package dev.damodaran.planets.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dev.damodaran.planets.R
import dev.damodaran.planets.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(), PlanetListFragment.OnPlanetSelectedListener {
    private var detailContiner: View? = null
    var isTwoPane = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main)
        detailContiner = findViewById(R.id.planetDetailContainer)
        isTwoPane = detailContiner != null

    }


    override fun onPlanetSelected(summary: String, thumbnail: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val planetDetailView = DetailFragment()
        val bundle = Bundle()
        bundle.putString(SUMMARY, summary)
        bundle.putString(IMAGE, thumbnail)
        planetDetailView.arguments = bundle

            if(isTwoPane){
                supportFragmentManager.beginTransaction().add(
                    R.id.planetDetailContainer,planetDetailView
                ).commit()
                return
            }


            fragmentTransaction.replace(R.id.fragmentContainer, planetDetailView)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


    }

    override fun onStop() {
        super.onStop()


    }
}