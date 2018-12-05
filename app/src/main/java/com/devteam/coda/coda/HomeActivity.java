package com.devteam.coda.coda;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView medCard,problemCard, apptCard, watchCard, callCard, summaryCard;
    private MenuItem myActionMenuItem;
    private EditText myActionEditText;
    private TextView usrName;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usrName = findViewById(R.id.usrnameTool);
        usrName.setText(LoginActivity.getUsername());


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

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.meds_card: i = new Intent(this,MedicationActivity.class);startActivity(i); break;
            case R.id.problems_card: i = new Intent(this,ProblemsActivity.class);startActivity(i); break;
            case R.id.appointment_card: i = new Intent(this,AppointmentActivity.class);startActivity(i); break;
            case R.id.watch_card: i = new Intent(this,WatchActivity.class);startActivity(i); break;
            case R.id.call_card: i = new Intent(this,CallActivity.class);startActivity(i); break;
            case R.id.summary_card: i = new Intent(this,PdfScrapper.class);startActivity(i); break;
            default:break;
        }

    }


}

