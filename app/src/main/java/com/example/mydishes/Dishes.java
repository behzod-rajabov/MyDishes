package com.example.mydishes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dishes")
public class Dishes {
    @PrimaryKey (autoGenerate = true)
    private int DishesId;

    @ColumnInfo(name = "DishesName")
    private String DishesName;

    @ColumnInfo(name = "DishesDescription")
    private String DishesDescription;

    @ColumnInfo(name = "DishesImage")
    private String DishesImage;

    @ColumnInfo(name = "DishesCategory_id")
    private int DishesCategoryId;

    @ColumnInfo(name = "fav")
    private boolean fav = false;

    public Dishes(int dishesId, String dishesName, String dishesDescription, String dishesImage, int dishesCategoryId, boolean fav) {
        DishesId = dishesId;
        DishesName = dishesName;
        DishesDescription = dishesDescription;
        DishesImage = dishesImage;
        DishesCategoryId = dishesCategoryId;
        this.fav = fav;
    }

    public Dishes() {

    }

    public int getDishesId() {
        return DishesId;
    }

    public void setDishesId(int dishesId) {
        DishesId = dishesId;
    }

    public String getDishesName() {
        return DishesName;
    }

    public void setDishesName(String dishesName) {
        DishesName = dishesName;
    }

    public String getDishesDescription() {
        return DishesDescription;
    }

    public void setDishesDescription(String dishesDescription) {
        DishesDescription = dishesDescription;
    }

    public String getDishesImage() {
        return DishesImage;
    }

    public void setDishesImage(String dishesImage) {
        DishesImage = dishesImage;
    }

    public int getDishesCategoryId() {
        return DishesCategoryId;
    }

    public void setDishesCategoryId(int dishesCategoryId) {
        DishesCategoryId = dishesCategoryId;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
