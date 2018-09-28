package com.diegoferreiracaetano.chuckNorris.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.diegoferreiracaetano.chuckNorris.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        NavigationUI.setupActionBarWithNavController(this, host.navController)

    }


    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}
