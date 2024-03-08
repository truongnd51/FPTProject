package com.example.fptproject.models;

public class Doctor {
    int id;
    String name;
    String username;
    String password;
    String price;
    String email;
    String phone;
    String description;
    String image;

    public Doctor() {
    }

    public Doctor(int id, String name, String username, String password, String price, String email, String phone, String description, String image) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.price = price;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.image = image;
    }

    public Doctor(String name, String username, String password, String email, String phone, String image) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public Doctor(String doctorName, String doctorEmail, String doctorPhone) {
        this.name = doctorName;
        this.email = doctorEmail;
        this.phone = doctorPhone;
    }

    public Doctor(String doctorName) {
        this.name = doctorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
