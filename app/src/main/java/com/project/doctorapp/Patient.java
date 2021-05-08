package com.project.doctorapp;

public class Patient {
    String patientName,Address,ContactNumber,DOB,Email,Password,Gender;

    public Patient() {
    }

    public Patient(String patient_Name ,String address, String contactNumber, String DOB, String email, String password, String gender) {
        patientName = patient_Name;
        Address = address;
        ContactNumber = contactNumber;
        this.DOB = DOB;
        Email = email;
        Password = password;
        Gender = gender;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
