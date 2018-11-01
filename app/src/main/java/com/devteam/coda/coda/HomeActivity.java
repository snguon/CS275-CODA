package com.devteam.coda.coda;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView medCard,problemCard, apptCard, watchCard, callCard, summaryCard;
    private MenuItem myActionMenuItem;
    private EditText myActionEditText;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //define the different cards
        medCard = (CardView) findViewById(R.id.meds_card);
        problemCard = (CardView) findViewById(R.id.problems_card);
        apptCard = (CardView) findViewById(R.id.appointment_card);
        watchCard = (CardView) findViewById(R.id.watch_card);
        callCard = (CardView) findViewById(R.id.call_card);
        summaryCard = (CardView) findViewById(R.id.summary_card);
        //Add click listener to the cards
        medCard.setOnClickListener(this);
        problemCard.setOnClickListener(this);
        apptCard.setOnClickListener(this);
        watchCard.setOnClickListener(this);
        callCard.setOnClickListener(this);
        summaryCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.meds_card: i = new Intent(this,MedicationActivity.class);startActivity(i); break;
            case R.id.problems_card: i = new Intent(this,ProblemsActivity.class);startActivity(i); break;
            case R.id.appointment_card: i = new Intent(this,AppointmentActivity.class);startActivity(i); break;
            case R.id.watch_card: i = new Intent(this,WatchActivity.class);startActivity(i); break;
            case R.id.call_card: i = new Intent(this,CallActivity.class);startActivity(i); break;
            case R.id.summary_card: i = new Intent(this,SummaryActivity.class);startActivity(i); break;
            default:break;
        }

    }
}

