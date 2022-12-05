package com.example.appbanhang.model;

import android.content.ClipData;

import java.util.List;

public class DonHang {
    int id;
    int iduser;
    String diachi;
    String sodienthoai;
    String tongtien;
    List<item> item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public List<com.example.appbanhang.model.item> getItem() {
        return item;
    }

    public void setItem(List<com.example.appbanhang.model.item> item) {
        this.item = item;
    }
}
