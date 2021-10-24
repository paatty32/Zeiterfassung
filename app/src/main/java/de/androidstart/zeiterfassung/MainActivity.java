package de.androidstart.zeiterfassung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import de.androidstart.zeiterfassung.db.WorkTime;
import de.androidstart.zeiterfassung.db.WorkTimeDatabase;

public class MainActivity extends AppCompatActivity {

    //Klassenvariablen für die Views
    private EditText startDatetime;
    private EditText endDateTime;

    private Button startCommand;
    private Button endCommand;

    //Klassenvariablen für die Formatierung der Uhrzeit und des Datums
    private DateFormat dateFormatter;
    private DateFormat timeFormatter;

    @Override
    //Klassicher Konstruktor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Verbindung zwischen Layout Datai und Java Klasse

        //Suchen der IDs um die in der Klasse bekannt zu machen.
        startDatetime = findViewById(R.id.StartDateTime);
        endDateTime = findViewById(R.id.EndDateTime);
        startCommand = findViewById(R.id.StartCommand);
        endCommand = findViewById(R.id.EndCommand);

        //Initialisierung
        //Erfragen der Betriebssystemeinstellung für die Formatierung von Zeit und Datum
        dateFormatter = android.text.format.DateFormat.getDateFormat(this);
        timeFormatter = android.text.format.DateFormat.getTimeFormat(this);

        //Aktuelle Uhrzeit anzeigen lassen
        //startDatetime.setText(Calendar.getInstance().getTime().toString());
    }

    //Regestrierung von möglichen Events
    @Override
    protected void onResume() {
        super.onResume();

        //Listener Registrieren
        startCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Logging
                Log.d("MainActivity", "onClick für Start-Button aufgerufen"); // Log Nachricht
                //Toast
                Toast.makeText(MainActivity.this, "onClick für Start-Button aufgerufen," ,
                        Toast.LENGTH_LONG).show();

                //Datumausgabe in der UI
                startDatetime.setText(getCurrentDateTime());

                //In Datenbank speichern
                final TimeTrackingApp app = (TimeTrackingApp) getApplication();
                app.getExecutors().get_diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        WorkTime workTime = new WorkTime();
                        workTime.startTime = getCurrentDateTime();
                        app.getDB().workTimeDao().add(workTime);
                    }
                });
            }
        });

        endCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Datum ausgabe in der UI
                endDateTime.setText(getCurrentDateTime());
            }
        });
    }

    /**
     * Formatiert die Aktuelle Zeit in ein lesbares Format.
     * @return Formatierte Uhrzeit und Datum.
     */
    private String getCurrentDateTime(){
        Calendar currentTime = Calendar.getInstance();
        return String.format("%s %s",
                dateFormatter.format(currentTime.getTime()), //Anwendung der Fomratierung
                timeFormatter.format(currentTime.getTime()));
    }


    //Deregistrierung
    @Override
    protected void onPause() {
        super.onPause();

        //Listener Deregistrierung
        startCommand.setOnClickListener(null);
        endCommand.setOnClickListener(null);
    }
}