package com.devteam.coda.coda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapted listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        listView = (ExpandableListView) findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapted(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Patient Information");
        listDataHeader.add("Problem List");
        listDataHeader.add("Medications");
        listDataHeader.add("Appointment Schedule");
        listDataHeader.add("What to Watch For");
        listDataHeader.add("Contact On-Call Team");
        listDataHeader.add("Discharge Summary");


        List<String> patientinf = new ArrayList<>();
        patientinf.add("Insert Patient info here");
        patientinf.add("Patient Name: " + getText(R.string.patient_name));

        List<String> problemList = new ArrayList<>();
        problemList.add("Problem 1");
        problemList.add("Problem 2");
        problemList.add("Problem 3");
        problemList.add("Problem 4");

        List<String> medicationList = new ArrayList<>();
        medicationList.add("Medication 1");
        medicationList.add("Medication 2");
        medicationList.add("Medication 3");
        medicationList.add("Medication 4");

        List<String> appointment = new ArrayList<>();
        appointment.add("Date of future Appointments:" +
                "\nDD/MM/YYYY -- Reason for visit\nDD/MM/YYYY -- Reason for visit");

        List<String> watchFor = new ArrayList<>();
        watchFor.add("insert info here later...");
        watchFor.add("insert info here later...");
        watchFor.add("insert info here later...");

        List<String> contact = new ArrayList<>();
        contact.add("Contact on call team?");

        List<String>dischargeSummary = new ArrayList<>();
        dischargeSummary.add("Discharge info");
        dischargeSummary.add("Discharge info");
        dischargeSummary.add("Discharge info");

        listHash.put(listDataHeader.get(0),patientinf);
        listHash.put(listDataHeader.get(1),problemList);
        listHash.put(listDataHeader.get(2),medicationList);
        listHash.put(listDataHeader.get(3),appointment);
        listHash.put(listDataHeader.get(4),watchFor);
        listHash.put(listDataHeader.get(5),contact);
        listHash.put(listDataHeader.get(6),dischargeSummary);
    }
}
