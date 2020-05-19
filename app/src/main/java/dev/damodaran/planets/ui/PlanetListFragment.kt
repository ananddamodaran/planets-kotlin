package dev.damodaran.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.damodaran.planets.ENDPOINT
import dev.damodaran.planets.R
import dev.damodaran.planets.api.PlanetsApiService
import dev.damodaran.planets.api.Response
import kotlinx.android.synthetic.main.fragment_planet_list.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


class PlanetListFragment(val listener : PlanetsAdapter.OnPlanetSelectedListener) : Fragment(){

    private lateinit var api: PlanetsApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cacheSize:Long = 10 * 1024 * 1024 // 10 MB

        val cache = Cache(activity?.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
         api = retrofit.create(PlanetsApiService::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_planet_list,container,false)
        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager

        api.getPlanets().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Timber.d("onResponse")
                if (response.isSuccessful) {
                    Timber.d(response.body()?.planets?.size.toString())
                    val list = response.body()?.planets ?: emptyList()
                    val adapter=PlanetsAdapter(list = list,listener = listener)
                    recyclerView.adapter =adapter
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Timber.d("onFailure ${t.localizedMessage}")
            }
        })

    }


}