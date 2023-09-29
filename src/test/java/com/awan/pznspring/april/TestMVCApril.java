package com.awan.pznspring.april;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMVCApril {

    List<MahasiswaModel> mahasiswaList = new ArrayList<>();


    @Test
    void testGetAll() {

        MahasiswaModel awan = new MahasiswaModel();
        awan.setId(1);
        awan.setDateMasuk("12012019");
        awan.setName("Yuyun Purniawan");
        awan.setDomisili("Depok");
        awan.setTanggal_lahir("12012002");
        awan.setKelas("08TPLE019");

        MahasiswaModel budi = new MahasiswaModel();
        budi.setId(12);
        budi.setDateMasuk("12012019");
        budi.setName("Budi Santoso");
        budi.setDomisili("Jakarta");
        budi.setTanggal_lahir("20012002");
        budi.setKelas("08TPLE019");

        mahasiswaList.add(awan);
        mahasiswaList.add(budi);


        MahasiswaController mahasiswaController = new MahasiswaController(mahasiswaList);
        mahasiswaController.getAllDataMahasiswa();





    }
}
