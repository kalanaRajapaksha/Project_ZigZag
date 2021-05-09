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

public class DoctorNoteView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DoctorNote> notes;
    SwipeRefreshLayout swiperefreshlayout;
    private  DoctorNoteAdapter myAdapter;
    DatabaseReference dbRef;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_note_view);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notes = new  ArrayList<DoctorNote>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("DoctorNote");
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
                DoctorNote dData = snapshot1.getValue(DoctorNote.class);
                notes.add(dData);
            }
            myAdapter= new DoctorNoteAdapter(DoctorNoteView.this,notes);
            recyclerView.setAdapter(myAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    public void addNote(View view){
        Toast.makeText(getApplicationContext(), "Showing create note...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DoctorNoteView.this, CreateDoctorNote.class);
        startActivity(intent);
    }

    public void noteview(View view){
        Toast.makeText(getApplicationContext(),"Showing Notes...", Toast.LENGTH_SHORT).show();
    }

    public void viewClinic(View view){
        Toast.makeText(getApplicationContext(),"Showing Clinic...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(DoctorNoteView.this , Doc_dashboard.class);
        startActivity(i);
    }
}