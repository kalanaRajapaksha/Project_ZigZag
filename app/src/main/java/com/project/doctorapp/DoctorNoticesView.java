package com.project.doctorapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorNoticesView extends AppCompatActivity {


    private RecyclerView recyclerView;

    private ArrayList<DoctorNotices> notes;

    SwipeRefreshLayout swiperefreshlayout;
    private  DoctorAdapter myAdapter;
    DatabaseReference dbRef;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_notices_view);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notes = new  ArrayList<DoctorNotices>();


        dbRef = FirebaseDatabase.getInstance().getReference().child("DoctorNotices");
        dbRef.addListenerForSingleValueEvent(valueEventListener);

        swiperefreshlayout = findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter.notifyDataSetChanged();
                swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1: snapshot.getChildren()){

                DoctorNotices dData = snapshot1.getValue(DoctorNotices.class);

                notes.add(dData);
            }
            myAdapter= new DoctorAdapter(DoctorNoticesView.this,notes);
            recyclerView.setAdapter(myAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    public void addNote(View view){
        Toast.makeText(getApplicationContext(), "Showing create note...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreateDoctorNotice.class);
        startActivity(intent);
    }

    public void clinicview(View view){
        Toast.makeText(getApplicationContext(),"Showing Clinic...", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(DoctorNoticesView.this, Doc_dashboard.class);
        startActivity(in);
    }

    public void noteview(View view){
        Toast.makeText(getApplicationContext(),"Showing Notice...", Toast.LENGTH_SHORT).show();
    }

    public void viewPatient(View view){

        Toast.makeText(getApplicationContext(),"Showing Patient...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(DoctorNoticesView.this , DoctorViewActivity.class);
        startActivity(i);
    }
}