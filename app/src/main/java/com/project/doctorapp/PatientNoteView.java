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

public class PatientNoteView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<PatientNote> notes;
    SwipeRefreshLayout swiperefreshlayout;
    private PatientNoteAdapter myAdapter;
    DatabaseReference dbRef;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paient_note_view);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notes = new  ArrayList<PatientNote>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("PatientNote");
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
                PatientNote pData = snapshot1.getValue(PatientNote.class);
                notes.add(pData);
            }
            myAdapter= new PatientNoteAdapter(PatientNoteView.this,notes);
            recyclerView.setAdapter(myAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void addNote(View view){
        Toast.makeText(getApplicationContext(), "Showing create note...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreatePaientNote.class);
        startActivity(intent);
    }

    public void noteView(View view){
        Toast.makeText(getApplicationContext(), "Showing Notes...", Toast.LENGTH_SHORT).show();
    }

    public void clinicView(View view){
        Toast.makeText(getApplicationContext(), "Showing Clinic...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(PatientNoteView.this , Pat_dashboard.class);
        startActivity(i);
    }

    public void vieHome(View view){
        Intent in = new Intent(PatientNoteView.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(PatientNoteView.this, PatientViewProfile.class);
        startActivity(in);
    }

}