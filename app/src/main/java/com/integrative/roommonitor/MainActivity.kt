package com.integrative.roommonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.integrative.roommonitor.databinding.ActivityMainBinding
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.mikepenz.materialdrawer.iconics.iconicsIcon
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.util.ExperimentalNavController
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.util.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @ExperimentalNavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityToolbar)

        binding.activityNavDrawer.apply {
            addItems(
                NavigationDrawerItem(R.id.navigation_rooms_fragment, PrimaryDrawerItem().apply {
                    nameRes = R.string.fragment_rooms_title
                    iconicsIcon = CommunityMaterial.Icon3.cmd_map_marker
                })
            )
        }

        navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.activityDrawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.activityNavDrawer.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onBackPressed() {
        binding.activityDrawerLayout.apply {
            if (isOpen) {
                close()
            } else {
                super.onBackPressed()
            }
        }
    }
}