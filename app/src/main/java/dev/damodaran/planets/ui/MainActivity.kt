package dev.damodaran.planets.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import dev.damodaran.planets.R
import dev.damodaran.planets.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(), PlanetListFragment.OnPlanetSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    override fun onPlanetSelected(summary: String, thumbnail: String) {
        Timber.d("name: $summary, thumbnail: $thumbnail")
        val detailFragment = supportFragmentManager.findFragmentById(R.id.planetDetail)
        if (detailFragment != null) {
            val detail = detailFragment as DetailFragment
            detail.updateView(summary,thumbnail)
        } else {

            val fragmentTransaction = supportFragmentManager.beginTransaction()

            val planetDetailView = DetailFragment()
            val bundle = Bundle()
            bundle.putString(SUMMARY, summary)
            bundle.putString(IMAGE, thumbnail)
            planetDetailView.arguments = bundle

            fragmentTransaction.replace(R.id.fragmentContainer, planetDetailView)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}