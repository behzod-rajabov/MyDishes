package com.example.mydishes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Category.class}, version = 1)
public abstract class MyAppDataBase extends RoomDatabase {
    public abstract MyDao myDao();
}
