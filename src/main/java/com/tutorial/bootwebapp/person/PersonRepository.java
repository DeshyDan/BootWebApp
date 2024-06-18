package com.tutorial.bootwebapp.person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PersonRepository {

    private final AtomicInteger idCounter = new AtomicInteger(0);


    private List<Person> people = new ArrayList<>();


    {
        people.add(new Person(idCounter.incrementAndGet(), "Deshy", 4, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Jonh", 5, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Jane", 6, Gender.FEMALE));


    }

    public AtomicInteger getIdCounter() {
        return idCounter;
    }

    public boolean isPersonAvailable(String name) {

        return people.stream()
                .anyMatch(person -> person.getName().equals(name));
    }

    public List<Person> getPeople() {
        return people;
    }
}
