package com.example.appbanhang.model;

public class ContryCodes {
    private int image;
    private String number;

    public ContryCodes() {
    }

    public ContryCodes(int image, String number) {
        this.image = image;
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
