package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditPatientNote extends AppCompatActivity {

    EditText etName,etDes;
    Button upBtn;
    String userId,name,des;
    private PatientNoteAdapter myAdapter;
    SwipeRefreshLayout swiperefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paient_note);

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

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PatientNote").child(userId);
                String uName,uDes;
                uName = etName.getText().toString();
                uDes = etDes.getText().toString();
                PatientNote note = new PatientNote(userId,uName,uDes, date);
                databaseReference.setValue(note);
                Toast.makeText(EditPatientNote.this,"Noted Updated "+name,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), PatientNoteView.class);
                v.getContext().startActivity(i);

            }
        });
    }

    public void vieHome(View view){
        Intent in = new Intent(EditPatientNote.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(EditPatientNote.this, PatientViewProfile.class);
        startActivity(in);
    }
}