package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Doc_dashboard extends AppCompatActivity {

Button create,refresh;
ImageView btn;
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
            btn = findViewById(R.id.profileviewdoc);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            clinic1 = new  ArrayList<Clinic>();

            dbRef = FirebaseDatabase.getInstance().getReference().child("Clinic Data");
            dbRef.addListenerForSingleValueEvent(valueEventListener);

        Intent profileIntent = getIntent();

        String DoctorName = profileIntent.getStringExtra("_docName");
        String DoctorContactNo = profileIntent.getStringExtra("_contactNo");
        String DoctorEmail = profileIntent.getStringExtra("_email");
        String DoctorDOB = profileIntent.getStringExtra("_dob");
        String DoctorAddress = profileIntent.getStringExtra("_address");
        String DoctorGender = profileIntent.getStringExtra("_gender");


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

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Doc_dashboard.this, DoctorViewProfile.class);
                    in.putExtra("_docName",DoctorName);
                    in.putExtra("_contactNo",DoctorContactNo);
                    in.putExtra("_email",DoctorEmail);
                    in.putExtra("_dob",DoctorDOB);
                    in.putExtra("_address",DoctorAddress);
                    in.putExtra("_gender",DoctorGender);
                    startActivity(in);
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

    public void noteView(View view){
        Toast.makeText(getApplicationContext(), "Showing Notices...", Toast.LENGTH_SHORT).show();
    }

    public void vieHome(View view){
        Intent in = new Intent(Doc_dashboard.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){

    }

    public void noteView(View view){
        Toast.makeText(this, "Showing Notes...", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(Doc_dashboard.this, DoctorNoteView.class);
        startActivity(in);
    }

    public void clinicView(View view){
        Toast.makeText(this, "Showing Clinic...", Toast.LENGTH_SHORT).show();
    }

}
