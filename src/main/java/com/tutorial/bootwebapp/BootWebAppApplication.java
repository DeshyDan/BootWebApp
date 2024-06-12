package com.tutorial.bootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/persons/")
@SpringBootApplication
public class BootWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWebAppApplication.class, args);
    }


    public enum Gender {MALE, FEMALE}

    public enum SortingOrder {ASC, DESC}

    public record Person(int id, String name, int age, Gender gender) {

    }

    public record PersonUpdateRequest(String name, Integer age) {
    }

    private static AtomicInteger idCounter = new AtomicInteger(0);

    public static List<Person> people = new ArrayList<>();


    static {
        people.add(new Person(idCounter.incrementAndGet(), "Deshy", 4, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Jonh", 5, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Jane", 6, Gender.FEMALE));


    }


    @GetMapping("/")
    public List<Person> getPersons(
            @RequestParam(value = "sort",
                    required = false,
                    defaultValue = "ASC") SortingOrder order,
            @RequestParam(
                    value = "limit",
                    required = false,
                    defaultValue = "10") Integer limit
    ) {

        if (order == SortingOrder.ASC) {
            return people.stream()
                    .limit(limit)
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());

        }

        return people.stream()
                .limit(limit)
                .sorted(Comparator.comparing(Person::id).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) {

        return people.stream()
                .filter(person -> person.id == id)
                .findFirst();
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable Integer id) {
        return people.removeIf(person -> person.id == id) ? "Delete Successful" : "User was not found";
    }


    @PostMapping("")
    public void addPerson(@RequestBody Person person) {
        people.add(new Person(idCounter.incrementAndGet(), person.name, person.age, person.gender));
    }

    @PutMapping("/{id}")
    public void updatePersonById(@PathVariable("id") Integer id, @RequestBody PersonUpdateRequest updatedPerson) {

        people.stream()
                .filter(person -> person.id == id)
                .findFirst()
                .ifPresent(person -> {
                    var index = people.indexOf(person);

                    if (updatedPerson.name != null && !updatedPerson.name.isEmpty() && !updatedPerson.name.equals(person.name)) {
                        Person newPerson = new Person(person.id(), updatedPerson.name(), person.age, person.gender);
                        people.set(index, newPerson);
                    }
                });
    }

}
