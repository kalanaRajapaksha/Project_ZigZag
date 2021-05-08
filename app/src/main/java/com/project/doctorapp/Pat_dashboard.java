package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pat_dashboard extends AppCompatActivity {

    Button join,refresh;
    private RecyclerView recyclerView;
    private ArrayList<com.project.doctorapp.Clinic> clinic1;
    private com.project.doctorapp.P_ClinicAdapter clinicAdapter;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_dashboard);
        refresh=findViewById(R.id.pClinicBtn);
        join = findViewById(R.id.joinBtn1);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clinic1 = new  ArrayList<com.project.doctorapp.Clinic>();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Clinic Data");
        dbRef.addListenerForSingleValueEvent(valueEventListener);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.project.doctorapp.Pat_dashboard.this,JoinClinicActivity.class);
                startActivity(i);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.project.doctorapp.Pat_dashboard.this, com.project.doctorapp.Pat_dashboard.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override

        public void onDataChange(@NonNull DataSnapshot snapshot) {
            clinic1.clear();
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                com.project.doctorapp.Clinic uData = snapshot1.getValue(com.project.doctorapp.Clinic.class);
                clinic1.add(uData);
            }
            clinicAdapter= new com.project.doctorapp.P_ClinicAdapter(com.project.doctorapp.Pat_dashboard.this,clinic1);
            recyclerView.setAdapter(clinicAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void noteView(View view){
        Toast.makeText(getApplicationContext(), "Showing Notes...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Pat_dashboard.this , PatientNoteView.class);
        startActivity(i);
    }

    public void clinicView(View view){
        Toast.makeText(getApplicationContext(), "Showing Clinic...", Toast.LENGTH_SHORT).show();
    }

    public void vieHome(View view){
        Intent in = new Intent(Pat_dashboard.this, app1page.class);
        startActivity(in);
    }
    public void profile(View view){
        Intent in = new Intent(Pat_dashboard.this, PatientViewProfile.class);
        startActivity(in);
    }

    public void alerm(View view){
        Intent in = new Intent(Pat_dashboard.this, reminderPatient.class);
        startActivity(in);
    }
}
