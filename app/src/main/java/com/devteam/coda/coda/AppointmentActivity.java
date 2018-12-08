package com.devteam.coda.coda;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private TextView usrName;
    private TextView myCalendar;
    private TextView appoint;
    private Date myMonth = new Date();
    private String text_appoint=PdfScrapper.get_apointments;

    private String getMonth(Date firstDayOfNewMonth){
        if(firstDayOfNewMonth.toString().contains("Jan")){
            return "January";
        }
        if(firstDayOfNewMonth.toString().contains("Feb")){
            return "February";
        }
        if(firstDayOfNewMonth.toString().contains("Mar")){
            return "March";
        }
        if(firstDayOfNewMonth.toString().contains("Apr")){
            return "April";
        }
        if(firstDayOfNewMonth.toString().contains("May")){
            return "May";
        }
        if(firstDayOfNewMonth.toString().contains("Jun")){
            return "June";
        }
        if(firstDayOfNewMonth.toString().contains("Jul")){
            return "July";
        }
        if(firstDayOfNewMonth.toString().contains("Aug")){
            return "August";
        }
        if(firstDayOfNewMonth.toString().contains("Sep")){
            return "September";
        }
        if(firstDayOfNewMonth.toString().contains("Oct")){
            return "October";
        }
        if(firstDayOfNewMonth.toString().contains("Nov")){
            return "November";
        }
        if(firstDayOfNewMonth.toString().contains("Dec")){
           return "December";
        }
        return "Month not found??";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myCalendar = findViewById(R.id.myCalendar);
        myCalendar.setText(getMonth(myMonth));
        usrName = findViewById(R.id.usrnameTool);
        usrName.setText(LoginActivity.getUsername());
        appoint = findViewById(R.id.myAppointments);
        if (text_appoint.equals("")){
            text_appoint = "Retrieve Data from Summary first";
        }
        appoint.setText(text_appoint);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);



        //December 01 2018
        final Event ev1 = new Event(Color.RED,1543575074000L, "December 1");
        compactCalendar.addEvent(ev1);


        //November 30 2018
        final Event ev2 = new Event(Color.GREEN, 1543661474000L, "This is tomorrow");
        compactCalendar.addEvent(ev2);


        //Jan 16 2019
        final Event ev3 = new Event(Color.GREEN, 1547635874000L, "My Birthday");
        compactCalendar.addEvent(ev3);

        //List<Event> events = compactCalendar.getEvents(1543575074000L); // can also take a Date object


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                String evDataString = "";
                Context context = getApplicationContext();

                List<Event> events = compactCalendar.getEvents(dateClicked);

                System.out.println(dateClicked.toString());
                if(dateClicked.toString().compareTo("Fri Nov 30 00:00:00 EST 2018") == 0){

                    evDataString = String.valueOf(ev2.getData());
                }

                if(dateClicked.toString().compareTo("Sat Dec 01 00:00:00 EST 2018") == 0){

                    evDataString = String.valueOf(ev1.getData());
                }

                if(dateClicked.toString().compareTo("Wed Jan 16 00:00:00 EST 2019") == 0){

                    evDataString = String.valueOf(ev3.getData());
                }

                Toast.makeText(context, "Events occurring: " + evDataString, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                System.out.println(firstDayOfNewMonth);
                myCalendar.setText(getMonth(firstDayOfNewMonth));


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
