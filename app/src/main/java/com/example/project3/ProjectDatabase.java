package com.example.project3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the class that holds the database
 * entities are held here
 */
@Database(entities = {User.class, Heroes.class}, version=1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
//    public abstract UserDao user();
    public abstract HeroesDao hero();

    private static ProjectDatabase sInstance;

    /**
     * Singleton method to ensure we only have one instance of our database
     * Checks to see if we have already created an instance, if we haven't, we
     * go ahead and create an instance of our database, otherwise, we return the
     * instance of the database we already have.
     *
     * HeroBrawl.db is the name of our database.
     */
    public static synchronized ProjectDatabase getInstance(Context context) {
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ProjectDatabase.class, "HeroBrawl.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    /**
     * Adding a user and hero to our database
     */
//    public void populateInitialData() {
//        runInTransaction(() -> {
//            if(user().count() == 0) {
//                user().addUser(
//                        new User("administrator", "abc")
//                );
//            }
//
//
//            if(hero().count() == 0) {
//                hero().addHero(
//                        new Heroes(1, "Cat", 1)
//                );
//            }
//        });
//    }

    public abstract UserDao getUserDao();
}
