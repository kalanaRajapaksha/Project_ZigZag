package com.project.doctorapp;

public class Doctor {
    String DocName, ContactNumber, Gender, DOB, Address, Password, Email;

    public Doctor() {
    }

    public Doctor(String doctorN, String contactNumber, String gender, String DOB, String address, String password, String email) {
        DocName = doctorN;
        ContactNumber = contactNumber;
        Gender = gender;
        this.DOB = DOB;
        Address = address;
        Password = password;
        Email = email;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
