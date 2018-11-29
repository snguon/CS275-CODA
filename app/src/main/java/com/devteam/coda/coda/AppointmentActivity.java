package com.devteam.coda.coda;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentActivity extends AppCompatActivity {


    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    private MenuItem myActionMenuItem;
    private EditText myActionEditText;
    private Toolbar toolbar;

    CalendarView calendarView;
    TextView myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //set event
        Event ev1 = new Event(Color.RED,1543575074000L, "Random Date");
        compactCalendar.addEvent(ev1);

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        Event ev2 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
        compactCalendar.addEvent(ev2);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev3 = new Event(Color.GREEN, 1433704251000L);
        compactCalendar.addEvent(ev3);

        List<Event> events = compactCalendar.getEvents(1543575074000L); // can also take a Date object


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                List<Event> events = compactCalendar.getEvents(dateClicked);

                if(dateClicked.toString().compareTo("Fri Nov 30 09:00:00 AST 2018") == 0){
                    Toast.makeText(context, "Random Date", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "No events on this date", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                //myCalendar.setText(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id == R.id.action_settings){
            Toast.makeText(getApplicationContext(),
                    "You selected settings option", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
