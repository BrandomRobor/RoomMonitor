package com.integrative.roommonitor.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.integrative.roommonitor.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings, rootKey)
    }
}