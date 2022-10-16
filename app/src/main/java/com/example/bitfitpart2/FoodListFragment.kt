package com.example.bitfitpart2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class FoodListFragment : Fragment() {
    private val food = mutableListOf<FoodEntity>()
    private lateinit var foodRV: RecyclerView
    private lateinit var foodRVAdapter: FoodRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Call the new method within onViewCreated
        //fetchArticles()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
            /*.also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            foodRV.addItemDecoration(dividerItemDecoration)
        }

             */

        foodRV = view.findViewById(R.id.rvFoodList)
        foodRV.layoutManager = layoutManager
        foodRV.setHasFixedSize(true)
        foodRVAdapter = FoodRVAdapter(view.context, food)

        lifecycleScope.launch {
            (activity?.application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.nameFood, entity.caloriesFood, entity.categoryFood)
                } .also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodRVAdapter.notifyDataSetChanged()
                }
            }
        }


        foodRV.adapter = foodRVAdapter


        /** Set button onclick listener to navigate to AddFood screen*/
        view.findViewById<Button>(R.id.addFoodBTN).setOnClickListener {
            val intent = Intent(context, AddFood::class.java)
            startActivity(intent)
        }

        // Update the return statement to return the inflated view from above
        return view
    }
/*
    private fun fetchArticles() {
        /** Set up the Recycle View Food Adapter with articles */
        lifecycleScope.launch {
            (application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.nameFood, entity.caloriesFood, entity.categoryFood)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodRVAdapter.notifyDataSetChanged()
                }
            }
        }
    }

 */
}