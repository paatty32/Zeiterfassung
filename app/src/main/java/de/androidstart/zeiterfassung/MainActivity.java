package de.androidstart.zeiterfassung;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Klassenvariablen für die Views
    private EditText startDatetime;
    private EditText endDateTime;

    private Button startCommand;
    private Button endCommand;

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
                Calendar currentTime = Calendar.getInstance();
                startDatetime.setText(currentTime.getTime().toString());
            }
        });

        endCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Datum ausgabe in der UI
                Calendar currentEndTime = Calendar.getInstance();
                endDateTime.setText(currentEndTime.getTime().toString());
            }
        });
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