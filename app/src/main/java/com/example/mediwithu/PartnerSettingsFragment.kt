package com.example.mediwithu

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class PartnerSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.partner_preference, rootKey)

        val partnerIdPreferences : EditTextPreference? = findPreference("partner_id")
        val partnerNamePreferences : EditTextPreference? = findPreference("partner_name")
        val partnerTelPreferences : EditTextPreference? = findPreference("partner_tel")
        val defaultMessagePreferences : EditTextPreference? = findPreference("default_message_pre")

        partnerIdPreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
            val id_text = preference.text
            if(TextUtils.isEmpty(id_text)){
                "파트너 ID(닉네임) 설정이 되지 않았습니다."
            }
            else{
                "설정된 파트너의 ID는 $id_text 입니다."
            }
        }
        partnerNamePreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
            val name_text = preference.text
            if(TextUtils.isEmpty(name_text)){
                "파트너 실명 설정이 되지 않았습니다."
            }
            else{
                "설정된 파트너는 $name_text 님 입니다."
            }
        }
        partnerTelPreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
            val tel_text = preference.text
            if(TextUtils.isEmpty(tel_text)){
                "파트너 전화번호 설정이 되지 않았습니다."
            }
            else{
                "$tel_text"
            }
        }

        defaultMessagePreferences?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
            val message_text = preference.text
            if(TextUtils.isEmpty(message_text)){
                "문자 보내기 기능 사용 시, 자동으로 입력될 문자 내용이 설정되지 않았습니다."
            }
            else{
                "$message_text"
            }
        }
    }

}