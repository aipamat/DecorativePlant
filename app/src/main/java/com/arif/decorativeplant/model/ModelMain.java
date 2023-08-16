package com.arif.decorativeplant.model;

import java.io.Serializable;

public class ModelMain implements Serializable {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    // Deklarasi variabel-variabel yang akan digunakan dalam kelas ini.
    String nama;
    String image;
    String deskripsi;

    // Getter untuk mendapatkan nilai nama.
    public String getNama() {
        return nama;
    }

    // Setter untuk mengatur nilai nama.
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk mendapatkan nilai image (URL gambar).
    public String getImage() {
        return image;
    }

    // Setter untuk mengatur nilai image (URL gambar).
    public void setImage(String image) {
        this.image = image;
    }

    // Getter untuk mendapatkan nilai deskripsi/manfaat tanaman.
    public String getDeskripsi() {
        return deskripsi;
    }

    // Setter untuk mengatur nilai deskripsi/manfaat tanaman.
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}
