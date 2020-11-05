package com.integrative.roommonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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

    @ExperimentalNavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityNavDrawer.apply {
            addItems(
                NavigationDrawerItem(R.id.roomsFragment, PrimaryDrawerItem().apply {
                    nameRes = R.string.fragment_rooms_title
                    iconicsIcon = CommunityMaterial.Icon3.cmd_map_marker
                })
            )
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfig = AppBarConfiguration(navController.graph, binding.activityDrawerLayout)
        binding.activityToolbar.setupWithNavController(navController, appBarConfig)
        binding.activityNavDrawer.setupWithNavController(navController)

        setSupportActionBar(binding.activityToolbar)
    }
}