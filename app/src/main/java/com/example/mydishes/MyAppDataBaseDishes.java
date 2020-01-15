package com.example.mydishes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Dishes.class}, version = 1)
public abstract class MyAppDataBaseDishes extends RoomDatabase {
    public abstract MyDaoDishes myDao();
}
