package com.devteam.coda.coda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MedicationActivity extends AppCompatActivity {

    private MenuItem myActionMenuItem;
    private EditText myActionEditText;
    private TextView usrName;
    private Toolbar toolbar;
    private TextView med;
    private String text_meds=PdfScrapper.get_meds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usrName = findViewById(R.id.usrnameTool);
        usrName.setText(LoginActivity.getUsername());
        med = findViewById(R.id.myMeds);
        med.setText(text_meds);
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
