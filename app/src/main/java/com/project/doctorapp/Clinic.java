package com.project.doctorapp;

public class Clinic {
    String id;
    String clinicName;
    String docName;
    String hospName;
    String dateTime;
    String desc;

    public Clinic() {
    }

    public Clinic(String id, String clinicName, String docName, String hospName, String dateTime, String desc) {
        this.id = id;
        this.clinicName = clinicName;
        this.docName = docName;
        this.hospName = hospName;
        this.dateTime = dateTime;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
