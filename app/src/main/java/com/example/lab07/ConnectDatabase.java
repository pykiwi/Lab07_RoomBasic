package com.example.lab07;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Person.class}, version = 1)
public abstract class ConnectDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "person.db";
    private static ConnectDatabase connectDB;

    public static ConnectDatabase getInstance(Context context) {
        if (connectDB == null) {
            connectDB = Room.databaseBuilder(context.getApplicationContext(), ConnectDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return connectDB;
    }
    public abstract PersonDAO personDAO();
}
