package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

public class DoctorNoticesPatientView extends AppCompatActivity {

    TextView cName;
    String clinicName;
    private RecyclerView recyclerView;
    private ArrayList<DoctorNote> pNotices;
    private PatientNoticesAdapter myAdapter;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_notices_patient_view);

        recyclerView = findViewById(R.id.recycler8);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pNotices = new  ArrayList<DoctorNote>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("DoctorNotices");
        dbRef.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                DoctorNote uData = snapshot1.getValue(DoctorNote.class);
                pNotices.add(uData);
            }
            myAdapter = new PatientNoticesAdapter(DoctorNoticesPatientView.this,pNotices);
            recyclerView.setAdapter(myAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void viewPatient(View view){
        Toast.makeText(this, "Showing Patients...", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(DoctorNoticesPatientView.this, PatientViewActivity.class);
        startActivity(in);
    }

    public void vieHome(View view){
        Intent in = new Intent(DoctorNoticesPatientView.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(DoctorNoticesPatientView.this, PatientViewProfile.class);
        startActivity(in);
    }
}