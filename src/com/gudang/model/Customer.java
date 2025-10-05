package com.gudang.model;

public class Customer {

    private String id;
    private String nama;
    private String email;
    private String telepon;

    public Customer(String id, String nama, String email, String telepon) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelepon() {
        return telepon;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
