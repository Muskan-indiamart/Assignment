package com.example.navigationdrawerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle=ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu.setNavigationItemSelectedListener(this)
        setToolbarTitle("Home")
        changeFragment(Home())

    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
            drawerLayout.closeDrawer(GravityCompat.START)

        when(item.itemId)
        {
            R.id.home ->{
                setToolbarTitle("Home")
                changeFragment(Home())


            }
            R.id.fragment1 ->{
                setToolbarTitle("Fragment 1")
                changeFragment(Fragment1())
            }
            R.id.fragment2 ->{
                setToolbarTitle("Fragment 2")
                changeFragment(Fragment2())
            }
            R.id.fragment3 ->{
                setToolbarTitle("Fragment 3")
                changeFragment(Fragment3())
            }
            R.id.fragment4 ->{
                setToolbarTitle("Fragment 4")
                changeFragment(Fragment4())
            }
//            R.id.fragment5 ->{
//                setToolbarTitle("Fragment 5")
//                changeFragment(Fragment5())
//            }
            R.id.new_activity->{
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }
            R.id.SharedPreferences->{
                val intent = Intent(this,MainActivity3::class.java)
                startActivity(intent)
            }
        }

            return true

    }
    fun setToolbarTitle(title : String){
        supportActionBar?.title=title
    }

    fun changeFragment(frag : Fragment)
    {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.container_fragment,frag).commit()
    }
}