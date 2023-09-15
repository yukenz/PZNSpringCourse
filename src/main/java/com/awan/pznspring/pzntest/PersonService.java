package com.awan.pznspring.pzntest;

import java.util.UUID;

public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(String id) {
        Person person = personRepository.selectById(id);

        if (person != null) {
            return person;

        }
        throw new IllegalArgumentException("Id not found");

    }

    public Person register(String name) {
        Person person = new Person(UUID.randomUUID().toString(), name);
        personRepository.insert(person);
        return person;
    }
}
