package com.example.foodorderapp;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@androidx.room.Database(entities = {UserTable.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public static MyDatabase instance;
    public abstract UserDao userDao();

    public static synchronized MyDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "user_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
