package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class Doc_dashboard extends AppCompatActivity {

Button create,refresh;
        private RecyclerView recyclerView;
        private ArrayList<Clinic> clinic1;
        private  ClinicAdapter clinicAdapter;
        DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_dashboard);
             refresh=findViewById(R.id.clinicBtn);
            create = findViewById(R.id.joinBtn1);
            recyclerView = findViewById(R.id.recycler);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            clinic1 = new  ArrayList<Clinic>();

            dbRef = FirebaseDatabase.getInstance().getReference().child("Clinic Data");
            dbRef.addListenerForSingleValueEvent(valueEventListener);

            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(com.project.doctorapp.Doc_dashboard.this,CreateClinicActivity.class);
                    startActivity(i);
                }
            });
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(com.project.doctorapp.Doc_dashboard.this, com.project.doctorapp.Doc_dashboard.class);
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
                    Clinic uData = snapshot1.getValue(Clinic.class);
                    clinic1.add(uData);
                }
                clinicAdapter= new ClinicAdapter(com.project.doctorapp.Doc_dashboard.this,clinic1);
                recyclerView.setAdapter(clinicAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    }
