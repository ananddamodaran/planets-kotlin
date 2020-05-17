package dev.damodaran.planets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.tag("Planets: MainActivity")

        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager




        val endpoint = "https://raw.githubusercontent.com/"
        val retrofit = Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())

                .build()

        val api = retrofit.create(PlanetsApiService::class.java)
        val response = api.getPlanets().enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Timber.d("onResponse")
                if(response.isSuccessful){
                   Timber.d(response.body()?.planets?.size.toString())
                    val list = response.body()?.planets?: emptyList()
                    recyclerView.adapter = PlanetsAdapter(list)
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
               Timber.d("onFailure ${t.localizedMessage}")


            }
        }

        )




    }
}