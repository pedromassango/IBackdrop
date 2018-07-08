package com.pedromassango.ibackbrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.main_backdrop.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // set toolbar as actionBar
        setSupportActionBar(home_toolbar)
        title = " "

        // build backdrop view
        backdrop_view
                .withToolbar(home_toolbar)
                .build()

        // on button click close backdrop
        btn_home.setOnClickListener {
            // call this to close backdrop
            backdrop_view.closeBackdrop()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
