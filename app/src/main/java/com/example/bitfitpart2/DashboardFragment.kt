package com.example.bitfitpart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        /** Adds new food item to the database */
        lifecycleScope.launch(Dispatchers.IO) {
            var numFood = (activity?.application as BitFitApplication).db.foodDao().countFood()
            view.findViewById<TextView?>(R.id.valueNumItems).text = numFood
            var sumCal = (activity?.application as BitFitApplication).db.foodDao().sumCalories()
            view.findViewById<TextView?>(R.id.valueTotCal).text = sumCal
            var average = sumCal.toInt() / numFood.toInt()
            view.findViewById<TextView?>(R.id.valueAveCal).text = average.toString()
        }

        return view
    }

}