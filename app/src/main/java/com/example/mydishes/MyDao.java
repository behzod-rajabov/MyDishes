package com.example.mydishes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao
{
    @Insert
    public void addCategory(Category category);

    @Query("select * from category")
    public List<Category> getCategory();

    @Query("DELETE from category where id = :id")
    public void deleteByIdCategory(int id);

    @Delete
    public void deleteCategory(Category category);

    @Update
    public void updateCategory(Category category);
}
