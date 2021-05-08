package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patientEditProfile extends AppCompatActivity {

    String _patientName,_patientContactNo,_patientAddress,_patientGender,_patientEmail,_patientDob;
    EditText contactNo,address,gender,email,dob;
    Button savebtn;
    TextView name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_profile);

        name = findViewById(R.id.patientEditName);
        contactNo = findViewById(R.id.patientEditPhone);
        address = findViewById(R.id.patientEditAddress);
        gender = findViewById(R.id.patientEditGender);
        email = findViewById(R.id.patientEditEmail);
        dob = findViewById(R.id.patientEditDOB);
        savebtn = findViewById(R.id.patientEditSave);

        Intent profileIntent = getIntent();
        String PatientName = profileIntent.getStringExtra("_patientName");
        String PatientContactNo = profileIntent.getStringExtra("_patientcontactNo");
        String PatientEmail = profileIntent.getStringExtra("_patientemail");
        String PatientDOB = profileIntent.getStringExtra("_patientdob");
        String PatientAddress = profileIntent.getStringExtra("_patientaddress");
        String PatientGender = profileIntent.getStringExtra("_patientgender");

        _patientName = PatientName;
        _patientContactNo = PatientContactNo;
        _patientAddress = PatientAddress;
        _patientGender = PatientGender;
        _patientEmail = PatientEmail;
        _patientDob = PatientDOB;

        name.setText(PatientName);
        contactNo.setText(PatientContactNo);
        address.setText(PatientAddress);
        gender.setText(PatientGender);
        email.setText(PatientEmail);
        dob.setText(PatientDOB);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
    private void update() {

        if(isContactNumberChanged() || isEmailChanged() || isDateOfBirthChanged() || isAddressChanged() || isGenderChanged()){

            Toast.makeText(this, "Update Successful!", Toast.LENGTH_SHORT).show();
            Intent editedintent =  new Intent(patientEditProfile.this, DoctorViewProfile.class);
            editedintent.putExtra("_patientName",_patientName);
            editedintent.putExtra("_patientcontactNo",_patientContactNo);
            editedintent.putExtra("_patientemail",_patientEmail);
            editedintent.putExtra("_patientdob",_patientDob);
            editedintent.putExtra("_patientaddress",_patientAddress);
            editedintent.putExtra("_patientgender",_patientGender);
            startActivity(editedintent);
        }else{
            Toast.makeText(this, "No data for update", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isContactNumberChanged(){
        if(!_patientContactNo.equals(contactNo.getText().toString().trim())){
            ref.child(_patientName).child("contactNumber").setValue(contactNo.getText().toString().trim());
            _patientContactNo = contactNo.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isEmailChanged() {
        if(!_patientEmail.equals(email.getText().toString().trim())){
            ref.child(_patientName).child("email").setValue(email.getText().toString().trim());
            _patientEmail = email.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isDateOfBirthChanged() {
        if(!_patientDob.equals(dob.getText().toString().trim())){
            ref.child(_patientName).child("dob").setValue(dob.getText().toString().trim());
            _patientDob = dob.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isAddressChanged() {
        if(!_patientAddress.equals(address.getText().toString().trim())){
            ref.child(_patientName).child("address").setValue(address.getText().toString().trim());
            _patientAddress = address.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isGenderChanged() {
        if(!_patientGender.equals(gender.getText().toString().trim())){
            ref.child(_patientName).child("gender").setValue(gender.getText().toString().trim());
            _patientGender = gender.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public void vieHome(View view){
        Intent in = new Intent(patientEditProfile.this, app1page.class);
        startActivity(in);
    }
}