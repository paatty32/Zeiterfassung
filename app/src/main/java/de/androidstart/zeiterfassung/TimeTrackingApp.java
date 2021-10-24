package de.androidstart.zeiterfassung;

import android.app.Application;

import de.androidstart.zeiterfassung.db.WorkTimeDatabase;

/**
 *
 */
public class TimeTrackingApp extends Application {

    private AppExecutors executors;

    /**
     * Damit wird sicher gestellt, dass die Executors immer zur Verfügung stehen.
     * -> onCreate() Methoden werden vor dem Start der Anwendung ausgeführt und nur einmal
     */
    @Override
    public void onCreate(){
        super.onCreate();

        this.executors = new AppExecutors();
    }

    public AppExecutors getExecutors(){
        return this.executors;
    }

    public WorkTimeDatabase getDB(){
        return WorkTimeDatabase.getInstance(this.getApplicationContext());
    }
}
