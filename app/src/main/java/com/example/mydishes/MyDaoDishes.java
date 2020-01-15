package com.example.mydishes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDaoDishes {
    // Dishes

    @Insert
    public void addDishes(Dishes dishes);

    @Query("select * from dishes")
    public List<Dishes> getDishes();

    @Query("select * from dishes where fav = 1")
    public List<Dishes> getFavDishes();

    @Query("select * from dishes where dishesId = :id")
    public Dishes getDishesById(int id);

    @Query("UPDATE dishes SET fav = 1 where dishesId = :id")
    public void favAddDishes(int id);

    @Query("UPDATE dishes SET fav = 0 where dishesId = :id")
    public void favDelDishes(int id);

    @Delete
    public void deleteDishes(Dishes dishes);

    @Update
    public void updateDishes(Dishes dishes);


}
