package com.arif.decorativeplant.model;

import java.io.Serializable;

public class ModelMain implements Serializable {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    String nama;
    String image;
    String deskripsi;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}
