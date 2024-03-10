package com.example.fptproject.models;

public class PhieuKham {
    String nameDoctor;
    String phoneDoctor;
    String namePatient;
    String phonePatient;
    String ngay;
    String gio;
    String benh;
    String gia;

    public PhieuKham(String nameDoctor, String phoneDoctor, String namePatient, String phonePatient, String ngay, String gio, String benh, String gia) {
        this.nameDoctor = nameDoctor;
        this.phoneDoctor = phoneDoctor;
        this.namePatient = namePatient;
        this.phonePatient = phonePatient;
        this.ngay = ngay;
        this.gio = gio;
        this.benh = benh;
        this.gia = gia;
    }

    public PhieuKham() {
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getPhoneDoctor() {
        return phoneDoctor;
    }

    public void setPhoneDoctor(String phoneDoctor) {
        this.phoneDoctor = phoneDoctor;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getPhonePatient() {
        return phonePatient;
    }

    public void setPhonePatient(String phonePatient) {
        this.phonePatient = phonePatient;
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

    public String getBenh() {
        return benh;
    }

    public void setBenh(String benh) {
        this.benh = benh;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
