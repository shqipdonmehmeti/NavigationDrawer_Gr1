package com.example.navigationdrawerproject_gr1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.navigationdrawerproject_gr1.databinding.ActivityMainBinding
import com.example.navigationdrawerproject_gr1.fragment.ATMFragment
import com.example.navigationdrawerproject_gr1.fragment.ContactFragment
import com.example.navigationdrawerproject_gr1.fragment.ExchangeFragment
import com.example.navigationdrawerproject_gr1.fragment.NotificationsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigation.setNavigationItemSelectedListener { menuItem ->
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawers()
            }
            when(menuItem.itemId) {
                R.id.notificationItem -> setFragment(NotificationsFragment())
                R.id.exchangeItem -> setFragment(ExchangeFragment())
                R.id.atmItem -> setFragment(ATMFragment())
                R.id.contactItem -> setFragment(ContactFragment())
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,fragment)
            addToBackStack("")
            commit()
        }
    }
}