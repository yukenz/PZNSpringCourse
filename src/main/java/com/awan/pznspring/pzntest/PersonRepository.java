package com.awan.pznspring.pzntest;

public interface PersonRepository {

    Person selectById(String id);

    void insert(Person person);

}
