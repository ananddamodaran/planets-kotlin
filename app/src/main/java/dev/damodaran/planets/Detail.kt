package dev.damodaran.planets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.planet_detail.*

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planet_detail)
        val img=intent.getStringExtra("image")
        val summaryVal=intent.getStringExtra("summary")

        Glide.with(this)
            .load(img)
            .centerCrop()
            .into(image)

        summary.text = summaryVal

    }
}