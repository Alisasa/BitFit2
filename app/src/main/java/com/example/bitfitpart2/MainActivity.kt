package com.example.bitfitpart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val food = mutableListOf<FoodEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val foodlist: Fragment = FoodListFragment()
        val dashboard: Fragment = DashboardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_log -> fragment = foodlist
                R.id.action_dashboard -> fragment = dashboard
            }
            fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.action_log
    }

    private fun replaceFragment(foodListFragment: FoodListFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, foodListFragment)
        fragmentTransaction.commit()
/*
        /** Set up the Recycle View Food Adapter with articles */
        val foodRV : RecyclerView = findViewById(R.id.rvFoodList)

        val foodAdapter = FoodRVAdapter(this, food)

        lifecycleScope.launch {
            (application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.nameFood, entity.caloriesFood, entity.categoryFood)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        foodRV.adapter = foodAdapter
        foodRV.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRV.addItemDecoration(dividerItemDecoration)
        }

        /** Set button onclick listener to navigate to AddFood screen*/
        findViewById<Button>(R.id.addFoodBTN).setOnClickListener{
            val intent = Intent(this, AddFood::class.java)
            startActivity(intent)
        }

 */
    }
}