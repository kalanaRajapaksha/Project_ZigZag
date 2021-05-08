package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signPatient extends AppCompatActivity {

    EditText usernameenter,passwordenter;
    ImageView signin;
    TextView signup;
    TextView clickhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_patient);

        usernameenter = findViewById(R.id.usernamePatientLogin);
        passwordenter = findViewById(R.id.passwordPatientLogin);

        signin = findViewById(R.id.signinbtnpatientlogin);
        clickhere = findViewById(R.id.doctorclickherepatientlogin);
        signup = findViewById(R.id.signUpPatient);

        signin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                LetThePatientLoggedIn();
            }
        });

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickhereintent = new Intent(signPatient.this,signDoctor.class);
                startActivity(clickhereintent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupintent = new Intent(signPatient.this,CreatePatientProfileActivity.class);
                startActivity(signupintent);
            }
        });


    }
    public void LetThePatientLoggedIn(){
        String _patientusername = usernameenter.getText().toString().trim();
        String _patientpassword = passwordenter.getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("Patient").orderByChild("patientName").equalTo(_patientusername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    usernameenter.setError(null);

                    String passwordDB = snapshot.child(_patientusername).child("password").getValue(String.class);
                    if (passwordDB.equals(_patientpassword)){

                        String _patientName = snapshot.child(_patientusername).child("patientName").getValue(String.class);
                        String _patientcontactNo = snapshot.child(_patientusername).child("contactNumber").getValue(String.class);
                        String _patientemail = snapshot.child(_patientusername).child("email").getValue(String.class);
                        String _patientdob = snapshot.child(_patientusername).child("dob").getValue(String.class);
                        String _patientaddress = snapshot.child(_patientusername).child("address").getValue(String.class);
                        String _patientgender = snapshot.child(_patientusername).child("gender").getValue(String.class);

                        Intent loginPatientIntent = new Intent(signPatient.this,PatientViewProfile.class);
                        loginPatientIntent.putExtra("_patientName",_patientName);
                        loginPatientIntent.putExtra("_patientcontactNo",_patientcontactNo);
                        loginPatientIntent.putExtra("_patientemail",_patientemail);
                        loginPatientIntent.putExtra("_patientdob",_patientdob);
                        loginPatientIntent.putExtra("_patientaddress",_patientaddress);
                        loginPatientIntent.putExtra("_patientgender",_patientgender);
                        startActivity(loginPatientIntent);

                    }else{
                        Toast.makeText(signPatient.this, "Password not correct!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(signPatient.this, "No user Exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(signPatient.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}