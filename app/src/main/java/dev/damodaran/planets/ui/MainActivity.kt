package dev.damodaran.planets.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.damodaran.planets.R
import timber.log.Timber

class MainActivity : AppCompatActivity() , PlanetsAdapter.OnPlanetSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.fragmentContainer,PlanetListFragment(this))
        fragmentTransaction.commit()

    }

    override fun onPlanetSelected(summary: String, thumbnail: String) {
        Timber.d("name: $summary, thumbnail: $thumbnail")
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val planetDetailView = DetailFragment()
        val bundle = Bundle()
        bundle.putString(SUMMARY, summary)
        bundle.putString(IMAGE, thumbnail)
        planetDetailView.arguments = bundle

        fragmentTransaction.replace(R.id.fragmentContainer,planetDetailView)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}