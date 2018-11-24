package com.alyssajinnellibed.spinner.model;

public class Diners {

    private int id;
    private String name;
    private String email;
    private String address;
    private String country;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return name;
    }

    public void setLocation(String name) {
        this.name = name;
    }

    public String getCategory() {
        return email;
    }

    public void setCategory(String email) {
        this.email = email;
    }

    public String getDinerName() {
        return address;
    }

    public void setDinerName(String address) {
        this.address = address;
    }

    public String getPriceRange() {
        return country;
    }

    public void setPriceRange(String country) {
        this.country = country;
    }
}