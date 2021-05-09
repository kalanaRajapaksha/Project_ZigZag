package com.project.doctorapp;


import android.content.Intent;
import android.os.Bundle;
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

public class EditDoctorNotice extends AppCompatActivity {

    EditText etName,etDes;
    Button upBtn;
    String userId,name,des;
    private  DoctorAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_notice);

        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);

        etName = findViewById(R.id.etName);
        etDes =  findViewById(R.id.etNote);
        upBtn = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        des = intent.getStringExtra("note");

        etName.setText(name);
        etDes.setText(des);

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DoctorNotices").child(userId);
                String uName,uDes;
                uName = etName.getText().toString();
                uDes = etDes.getText().toString();
                DoctorNote note = new DoctorNote(userId,uName,uDes, date);
                databaseReference.setValue(note);
                Toast.makeText(EditDoctorNotice.this,"Noted Updated "+name,Toast.LENGTH_SHORT).show();
                Intent in = new Intent(EditDoctorNotice.this, DoctorNoticesView.class);
                startActivity(in);
            }
        });

    }

}