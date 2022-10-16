package com.example.bitfitpart2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>

    @Query("SELECT sum(caloriesFood) FROM food_table")
    fun sumCalories() : String

    //@Query("SELECT AVE(caloriesFood) FROM food_table")
    //fun aveCalories() : String

    @Query("SELECT count(id) FROM food_table")
    fun countFood() : String

    @Insert
    fun insertAll(foodList: List<FoodEntity>)

    @Insert
    fun insert(foodItem: FoodEntity)

    @Query("DELETE FROM food_table")
    fun deleteAll()
}