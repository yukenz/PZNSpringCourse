package com.awan.pznspring.april;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MahasiswaModel {

    private Integer id;

    private Date dateMasuk;


    private String name;


    private Date tanggal_lahir;


    private String domisili;


    private String kelas;

    private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");


    public void setDateMasuk(String dateMasuk) {
        try {
            this.dateMasuk = sdf.parse(dateMasuk);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void setTanggal_lahir(String tanggal_lahir) {
        try {
            this.tanggal_lahir = sdf.parse(tanggal_lahir);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateMasuk() {
        return dateMasuk;
    }

    public void setDateMasuk(Date dateMasuk) {
        this.dateMasuk = dateMasuk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
