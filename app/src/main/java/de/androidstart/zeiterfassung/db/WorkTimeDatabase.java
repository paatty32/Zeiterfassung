package de.androidstart.zeiterfassung.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Klasse für den Zugriff auf die Daten der Datenbank vom Programmcode aus und enthält
 * auch die Entities
 */
@Database(entities = {WorkTime.class}, version = 1)
public abstract class WorkTimeDatabase extends RoomDatabase {

    public abstract WorkTimeDao workTimeDao();
}
