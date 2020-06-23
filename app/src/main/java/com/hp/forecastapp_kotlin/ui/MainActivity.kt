package com.hp.forecastapp_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.hp.forecastapp_kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //create nav controller
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        //connect bottom nav with nav controller
        bottom_nav.setupWithNavController(navController)

        //setup action bar with nav controller
        NavigationUI.setupActionBarWithNavController(this,navController)


    }

    //back button on action bar
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}