package com.example.fptproject.models;

public class HomeMenuDoctor {
    private int imgDoc;
    private String nameDoc;

    public HomeMenuDoctor(int imgDoc, String nameDoc) {
        this.imgDoc = imgDoc;
        this.nameDoc = nameDoc;
    }

    public int getImgDoc() {
        return imgDoc;
    }

    public void setImgDoc(int imgDoc) {
        this.imgDoc = imgDoc;
    }

    public String getNameDoc() {
        return nameDoc;
    }

    public void setNameDoc(String nameDoc) {
        this.nameDoc = nameDoc;
    }
}
