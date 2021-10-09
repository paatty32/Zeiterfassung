package de.androidstart.zeiterfassung.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Interface für den Zugriff auf Tabellen und Spalten
 */
@Dao
public interface WorkTimeDao {

    @Query("SELECT * FROM time_data")
    List<WorkTime> getAll();

    @Query("SELECT * FROM time_data WHERE _id = :id") //:id um mit Parametresierung zu arbeiten
    WorkTime getById(int id);

    @Insert
    void add(WorkTime workTime);
}
