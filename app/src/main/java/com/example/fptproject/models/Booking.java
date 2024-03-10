package com.example.fptproject.models;

public class Booking {
    int doctorId;
    int patientId;
    String ngay;
    String gio;
    int benh;

    public int getDoctorId() {
        return doctorId;
    }

    public Booking() {
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public int getBenh() {
        return benh;
    }

    public void setBenh(int benh) {
        this.benh = benh;
    }

    public Booking(int doctorId, int patientId, String ngay, String gio, int benh) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.ngay = ngay;
        this.gio = gio;
        this.benh = benh;
    }
}
