package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientViewActivity extends AppCompatActivity {
TextView cName;
    String clinicName;
    private RecyclerView recyclerView;
    private ArrayList<Patient> patient1;
    private com.project.doctorapp.P_PatientAdapter p_patientAdapter;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_view);
        cName = findViewById(R.id.clinicNameTxt2);
        Intent i = getIntent();
        clinicName = i.getStringExtra("clinicName");
        recyclerView = findViewById(R.id.recycler8);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        patient1 = new  ArrayList<Patient>();
        cName.setText(clinicName);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Patient");
        dbRef.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                Patient uData = snapshot1.getValue(Patient.class);
                patient1.add(uData);
            }
            p_patientAdapter= new com.project.doctorapp.P_PatientAdapter(com.project.doctorapp.PatientViewActivity.this,patient1);
            recyclerView.setAdapter(p_patientAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}