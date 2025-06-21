package com.example.mediwithu

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mediwithu.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
    val fragments: List<Fragment>
    init{
        fragments = listOf(ThreeFragment(), OneFragment(), FourFragment(), TwoFragment())
    }

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val emailPre = sharedPreference.getString("id", "${MyApplication.email}")
        val idPre = sharedPreference.getString("id", "${emailPre}")
        val colorPre = sharedPreference.getString("color", "#355E45")
        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너")

        binding.toolbar.setTitleTextColor(Color.parseColor(colorPre))

        if(MyApplication.checkAuth() || MyApplication.email != null){
            binding.toolbar.title = "${idPre} 님"
            binding.viewpageandtabs.visibility = View.VISIBLE
        }
        else{
            binding.toolbar.title = "MediWithU"
            binding.viewpageandtabs.visibility = View.GONE
        }

        setSupportActionBar(binding.toolbar)


        toggle = ActionBarDrawerToggle(this, binding.drawerMain, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.drawer.setNavigationItemSelectedListener(this)

        val fadapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = fadapter

        TabLayoutMediator(binding.tabs, binding.viewpager){
            tab, position -> tab.text = when(position) {
                0 -> "홈"
                1 -> "$partner_idPre"
                2 -> "복약 일지"
                3 -> "찾기"
                else -> "기타"
        }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        when (item.itemId){
            R.id.menu_login -> {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.menu_login)
        if(MyApplication.checkAuth() || MyApplication.email != null){
            item?.title = "로그아웃"
        }
        else{
            item?.title = "로그인"
        }
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onStart() {
        super.onStart()


        invalidateMenu()
        if(MyApplication.checkAuth() || MyApplication.email != null){
            binding.toolbar.title = "${MyApplication.email} 님"

            binding.viewpageandtabs.visibility = View.VISIBLE
        }
        else{
            binding.toolbar.title = "MediWithU"
            binding.viewpageandtabs.visibility = View.GONE
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_partner -> {
                val intent = Intent(this, PartnerSettingActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()

        val idPre = sharedPreference.getString("id", "${MyApplication.email}")
        val colorPre = sharedPreference.getString("color", "#355E45")

        if(MyApplication.checkAuth() || MyApplication.email != null){
            binding.toolbar.title = "${idPre} 님"
        }
        else{
            binding.toolbar.title = "MediWithU"
        }
        binding.toolbar.setTitleTextColor(Color.parseColor(colorPre))
    }

}