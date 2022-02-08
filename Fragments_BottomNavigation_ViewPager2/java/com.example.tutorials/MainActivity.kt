package com.example.tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imageList = listOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6
        )
        val adapter = ViewPagerAdapter(imageList)
        vpImageScroll.adapter = adapter

//        for vertical scroll
//        vpImageScroll.orientation = ViewPager2.ORIENTATION_VERTICAL

//        for fake drags
//        vpImageScroll.beginFakeDrag()
//        vpImageScroll.fakeDragBy(-4f)
//        vpImageScroll.endFakeDrag()

        TabLayoutMediator(tlTab, vpImageScroll) { tab, position ->
            tab.text = position.toString()
        }.attach()

        tlTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "You chose ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "You just left ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "${tab?.text}?. But Why?", Toast.LENGTH_SHORT).show()
            }
        })

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(homeFragment)
                R.id.miSearch -> setCurrentFragment(searchFragment)
                R.id.miProfile -> setCurrentFragment(profileFragment)
            }
            true
        }

        bottomNavigationView.getOrCreateBadge(R.id.miHome).apply{
            number = 3
            isVisible = true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, fragment)
            commit()
        }
}
