package com.example.mediwithu

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val idPreferences : EditTextPreference? = findPreference("id")
        val colorPreference : ListPreference? = findPreference("color")
        val emailPreferences : EditTextPreference? = findPreference("email")

        idPreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
            preference ->
            val id_text = preference.text
            if(TextUtils.isEmpty(id_text)){
                "ID 설정이 되지 않았습니다."
                }
            else{
                "설정된 ID는 $id_text 입니다."
            }
        }
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        emailPreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
            val email_text = preference.text
            if(TextUtils.isEmpty(email_text)){
                "${MyApplication.email}"
            }
            else{
                "$email_text"
            }
        }
    }
}