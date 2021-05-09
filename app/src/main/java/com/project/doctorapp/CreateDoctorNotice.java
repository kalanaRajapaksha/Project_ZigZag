package com.project.doctorapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateDoctorNotice extends AppCompatActivity {

    EditText etName, etNote;
    Button btnAddData,btnBack;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor_notice);

        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);


        etName = findViewById(R.id.etName);
        etNote = findViewById(R.id.etNote);


        btnAddData = findViewById(R.id.btnAddData);

        database = FirebaseDatabase.getInstance().getReference().child("DoctorNotices");

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id ,name, note;

                id = database.push().getKey();
                name = etName.getText().toString();
                note = etNote.getText().toString();

                if(TextUtils.isEmpty(etName.getText().toString())){
                    Toast.makeText(CreateDoctorNotice.this, "Name field is empty!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(etNote.getText().toString())){
                    Toast.makeText(CreateDoctorNotice.this, "Description field is empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    DoctorNote notes = new DoctorNote(id,name, note, date);
                    database.child(id).setValue(notes);

                    etName.setText("");
                    etNote.setText("");

                    Toast.makeText(getApplicationContext(), "Note Inserted ! "+notes.getName(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(CreateDoctorNotice.this, DoctorNoticesView.class);
                    startActivity(in);
                }
            }
        });

    }
}