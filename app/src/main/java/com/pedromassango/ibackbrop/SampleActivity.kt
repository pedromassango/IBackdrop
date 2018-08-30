package com.pedromassango.ibackbrop

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.main_backdrop.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // set toolbar as actionBar
        setSupportActionBar(home_toolbar)
        title = " " // I do not want a title


        /**
         * Backdrop View Initialize
         * @see R.attrs.Backdrop}
         * If used Backdrop.toolbar attribute
         * Do not need to use buildWithToolbar method
         *
         * If not,
         * @see com.pedromassango.ibackdrop.Backdrop.buildWithToolbar(toolbar : Toolbar)
         * */
        //  backdrop_view.buildWithToolbar(home_toolbar)

        // on button click close backdrop
        btn_home.setOnClickListener {

            // call this to close backdrop
            backdrop_view.closeBackdrop()

            // or this to open backdrop
            //backdrop_view.openBackdrop()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
