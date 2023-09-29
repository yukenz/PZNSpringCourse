package com.awan.pznspring.april;

import java.text.SimpleDateFormat;

public class MahasiswaView {

    public static String viewDataMahasiswa(MahasiswaModel mahasiswa) {

        StringBuilder strBuilder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Integer id = mahasiswa.getId();

        strBuilder.append("Nama").append(" : ").append(mahasiswa.getName()).append("\n")
                .append("NIM").append(" : ").append(sdf.format(mahasiswa.getDateMasuk()).toString() + (id < 10 ? "0" + id : id)).append("\n")
                .append("Kelas").append(" : ").append(mahasiswa.getKelas()).append("\n")
                .append("Domisili").append(" : ").append(mahasiswa.getDomisili()).append("\n");

        return strBuilder.toString();
    }

}
