package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class DoctorViewActivity extends AppCompatActivity {

    Button refresh;
    private RecyclerView recyclerView;
    private ArrayList<Patient> patient1;
    private  PatientAdapter patientAdapter;
    DatabaseReference dbRef;
    TextView cName;
    String clinicName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_view);
        cName = findViewById(R.id.clinicNameTxt);
        Intent i = getIntent();
        clinicName = i.getStringExtra("clinicName");
        refresh=findViewById(R.id.clinicBtn);
        recyclerView = findViewById(R.id.patRecycle);
        cName.setText(clinicName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        patient1 = new  ArrayList<Patient>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Patient");
        dbRef.addListenerForSingleValueEvent(valueEventListener);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), com.project.doctorapp.DoctorViewActivity.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }
    public void EditClinic(View view){
        Intent i = new Intent(com.project.doctorapp.DoctorViewActivity.this, com.project.doctorapp.Doc_dashboard.class);
        startActivity(i);
    }
    public void DelClinic(View view){
        Intent i = new Intent(com.project.doctorapp.DoctorViewActivity.this, com.project.doctorapp.Doc_dashboard.class);
        startActivity(i);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override

        public void onDataChange(@NonNull DataSnapshot snapshot) {
            patient1.clear();
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                Patient uData = snapshot1.getValue(Patient.class);
                patient1.add(uData);
            }
            patientAdapter= new PatientAdapter(com.project.doctorapp.DoctorViewActivity.this,patient1);
            recyclerView.setAdapter(patientAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void viewNoties(View view){
        Intent i = new Intent(DoctorViewActivity.this , DoctorNoticesView.class);
        startActivity(i);
    }

    public void vieHome(View view){
        Intent in = new Intent(DoctorViewActivity.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(DoctorViewActivity.this, DoctorViewProfile.class);
        startActivity(in);
    }

    public void viewPatient(View view){
        Toast.makeText(this, "Showing Patient...", Toast.LENGTH_SHORT).show();
    }

    public void viewNotice(View view){
        Toast.makeText(this, "Showing Notice...", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(DoctorViewActivity.this, DoctorNoticesView.class);
        startActivity(in);
    }
}
