package com.example.mediwithu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mediwithu.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
    val fragments: List<Fragment>
    init{
        fragments = listOf(ThreeFragment(), OneFragment(), TwoFragment())
    }

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(MyApplication.checkAuth() || MyApplication.email != null){
            binding.toolbar.title = "${MyApplication.email} 님"
        }
        else{
            binding.toolbar.title = "MediWithU"
        }

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawerMain, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val fadapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = fadapter

        TabLayoutMediator(binding.tabs, binding.viewpager){
            tab, position -> tab.text = "TAB ${position + 1}"
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
        }
        else{
            binding.toolbar.title = "MediWithU"
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

        }
        return false
    }

}