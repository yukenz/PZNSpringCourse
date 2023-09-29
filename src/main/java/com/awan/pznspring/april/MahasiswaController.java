package com.awan.pznspring.april;

import java.util.List;

public class MahasiswaController {

    private List<MahasiswaModel> mahasiswaList;

    public MahasiswaController(List<MahasiswaModel> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }

    public void getAllDataMahasiswa() {
        mahasiswaList.forEach(mahasiswaModel -> System.out.println(MahasiswaView.viewDataMahasiswa(mahasiswaModel)));
    }

}
