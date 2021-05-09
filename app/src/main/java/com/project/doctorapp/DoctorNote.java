package com.project.doctorapp;

public class DoctorNote {

    private String id;
    private String name;
    private String note;
    private String date;

    public DoctorNote() {
    }

    public DoctorNote(String id, String name, String note, String date) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
