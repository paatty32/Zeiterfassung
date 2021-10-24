package de.androidstart.zeiterfassung;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Ermöglicht unteranderem Datenbank Operationen im Hintergrund auszuführen
 */
public class AppExecutors {

    private final Executor _diskIO; // Thread für den Zugriff auf die Speichermedien
    private final Executor _networkIO; // Thread für den Zugriff auf das Netzwerk
    private final Executor _mainThread; //Zugriff auf den UI-Thread


    private AppExecutors(Executor _diskIO, Executor _networkIO, Executor _mainThread){
        this._diskIO = _diskIO;
        this._networkIO = _networkIO;
        this._mainThread = _mainThread;
    }

    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), //besitzt nur einen einzigen Thread wegen SQLite
             Executors.newFixedThreadPool(3), //kann bis zu 3 Thread gleichzeitig ausführen
             new MainThreadExecutor());
    }

    public Executor get_diskIO() {
        return _diskIO;
    }

    public Executor get_mainThread() {
        return _mainThread;
    }

    public Executor get_networkIO() {
        return this._networkIO;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
