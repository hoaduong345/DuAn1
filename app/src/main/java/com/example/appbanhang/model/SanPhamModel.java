package com.example.appbanhang.model;

import java.util.List;

public class SanPhamModel {
    boolean success;
    String message;
    List<SanPham> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SanPham> getResultt() {
        return result;
    }

    public void setResult(List<SanPham> result) {
        this.result = result;
    }
}
