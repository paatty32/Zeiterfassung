package de.androidstart.zeiterfassung;

import java.util.concurrent.Executor;

public class AppExecutors {

    private final Executor _diskIO; // Thread für den Zugriff auf die Speichermedien
    private final Executor _networkIO; // Thread für den Zugriff auf das Netzwerk
    private final Executor _mainThread; //Zugriff auf den UI-Thread
}
