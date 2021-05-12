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


public class doctorEditProfile extends AppCompatActivity {
    String _Name,_ContactNo,_Address,_Gender,_Email,_Dob;
    EditText contactNo,address,gender,email,dob;
    Button savebtn;
    TextView name;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Doctor");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit_profile);



        name = findViewById(R.id.docEditName);
        contactNo = findViewById(R.id.docEditPhone);
        address = findViewById(R.id.docEditAddress);
        gender = findViewById(R.id.docEditGender);
        email = findViewById(R.id.docEditEmail);
        dob = findViewById(R.id.docEditDOB);
        savebtn = findViewById(R.id.docEditSave);


        Intent profileIntent = getIntent();
        String DoctorName = profileIntent.getStringExtra("_doctorName");
        String DoctorContactNo = profileIntent.getStringExtra("_contactNo");
        String DoctorEmail = profileIntent.getStringExtra("_email");
        String DoctorDOB = profileIntent.getStringExtra("_dob");
        String DoctorAddress = profileIntent.getStringExtra("_address");
        String DoctorGender = profileIntent.getStringExtra("_gender");

        _Name = DoctorName;
        _ContactNo = DoctorContactNo;
        _Address = DoctorAddress;
        _Gender = DoctorGender;
        _Email = DoctorEmail;
        _Dob = DoctorDOB;


        name.setText(DoctorName);
        contactNo.setText(DoctorContactNo);
        address.setText(DoctorAddress);
        gender.setText(DoctorGender);
        email.setText(DoctorEmail);
        dob.setText(DoctorDOB);



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
            Intent editedintent =  new Intent(doctorEditProfile.this,DoctorViewProfile.class);
            editedintent.putExtra("_docName",_Name);
            editedintent.putExtra("_contactNo",_ContactNo);
            editedintent.putExtra("_email",_Email);
            editedintent.putExtra("_dob",_Dob);
            editedintent.putExtra("_address",_Address);
            editedintent.putExtra("_gender",_Gender);
            startActivity(editedintent);
        }else{
            Toast.makeText(this, "No data for update", Toast.LENGTH_SHORT).show();
        }

    }



    private boolean isContactNumberChanged(){
        if(!_ContactNo.equals(contactNo.getText().toString().trim())){
            ref.child(_Name).child("contactNumber").setValue(contactNo.getText().toString().trim());
            _ContactNo = contactNo.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isEmailChanged() {
        if(!_Email.equals(email.getText().toString().trim())){
            ref.child(_Name).child("email").setValue(email.getText().toString().trim());
            _Email = email.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isDateOfBirthChanged() {
        if(!_Dob.equals(dob.getText().toString().trim())){
            ref.child(_Name).child("dob").setValue(dob.getText().toString().trim());
            _Dob = dob.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isAddressChanged() {
        if(!_Address.equals(address.getText().toString().trim())){
            ref.child(_Name).child("address").setValue(address.getText().toString().trim());
            _Address = address.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isGenderChanged() {
        if(!_Gender.equals(gender.getText().toString().trim())){
            ref.child(_Name).child("gender").setValue(gender.getText().toString().trim());
            _Gender = gender.getText().toString();
            return true;
        }else{
            return false;
        }
    }
}