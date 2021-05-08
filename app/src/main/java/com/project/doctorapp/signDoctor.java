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


public class signDoctor extends AppCompatActivity {

    EditText usernameenter,passwordenter;
    ImageView signin;
    TextView signup;
    TextView clickhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_doctor);

        usernameenter = findViewById(R.id.usernamedoctorlogin);
        passwordenter = findViewById(R.id.passworddoctorlogin);

        signin = findViewById(R.id.signinbtndoctorlogin);
        clickhere = findViewById(R.id.patientclickheredoctorlogin);
        signup = findViewById(R.id.signUpDoctor);

        signin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                LetTheDoctorLoggedIn();
            }
        });

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickhereintent = new Intent(signDoctor.this,signPatient.class);
                startActivity(clickhereintent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupintent = new Intent(signDoctor.this,CreateDoctorProfileActivity.class);
                startActivity(signupintent);
            }
        });


    }
    public void LetTheDoctorLoggedIn(){
        String _username = usernameenter.getText().toString().trim();
        String _password = passwordenter.getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("Doctor").orderByChild("docName").equalTo(_username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    usernameenter.setError(null);

                    String passwordDB = snapshot.child(_username).child("password").getValue(String.class);
                    if (passwordDB.equals(_password)){

                        String _docName = snapshot.child(_username).child("docName").getValue(String.class);
                        String _contactNo = snapshot.child(_username).child("contactNumber").getValue(String.class);
                        String _email = snapshot.child(_username).child("email").getValue(String.class);
                        String _dob = snapshot.child(_username).child("dob").getValue(String.class);
                        String _address = snapshot.child(_username).child("address").getValue(String.class);
                        String _gender = snapshot.child(_username).child("gender").getValue(String.class);

                        Intent loginIntent = new Intent(signDoctor.this,DoctorViewProfile.class);
                        loginIntent.putExtra("_docName",_docName);
                        loginIntent.putExtra("_contactNo",_contactNo);
                        loginIntent.putExtra("_email",_email);
                        loginIntent.putExtra("_dob",_dob);
                        loginIntent.putExtra("_address",_address);
                        loginIntent.putExtra("_gender",_gender);
                        startActivity(loginIntent);

                    }else{
                        Toast.makeText(signDoctor.this, "Password not correct!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(signDoctor.this, "No user Exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(signDoctor.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}