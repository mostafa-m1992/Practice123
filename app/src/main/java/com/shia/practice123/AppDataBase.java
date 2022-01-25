package com.shia.practice123;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {RoomPerson.class}, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase(Context context){
        if (appDataBase == null)
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "user-database")
                    .allowMainThreadQueries()
                    .build();
        return appDataBase;
    }

    public abstract RoomDao getRoomDao();
}
