package de.androidstart.zeiterfassung.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Klasse für den Zugriff auf die Daten der Datenbank vom Programmcode aus und enthält
 * auch die Entities
 */
@Database(entities = {WorkTime.class}, version = 1)
public abstract class WorkTimeDatabase extends RoomDatabase {

    public abstract WorkTimeDao workTimeDao();

    private static WorkTimeDatabase instance;

    public static WorkTimeDatabase getInstance(final Context context){
        if(instance == null){
            synchronized (WorkTimeDatabase.class){ //Nur ein Aufurf auf die Klasse ist möglich
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WorkTimeDatabase.class,
                            "worktime_data.db"
                    ).build();
                }
            }
        } return instance;
    }
}
