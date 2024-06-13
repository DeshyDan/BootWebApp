package com.tutorial.bootwebapp.person;

import com.tutorial.bootwebapp.SortingOrder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(
            SortingOrder order,
            Integer limit
    ) {

        if (order == SortingOrder.ASC) {
            return personRepository.getPeople().stream()
                    .limit(limit)
                    .sorted(Comparator.comparing(Person::getId))
                    .collect(Collectors.toList());

        }

        return personRepository.getPeople().stream()
                .limit(limit)
                .sorted(Comparator.comparing(Person::getId).reversed())
                .collect(Collectors.toList());
    }


    public Optional<Person> getPersonById(Integer id) {

        return personRepository.getPeople().stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    public String deletePersonById(Integer id) {
        return personRepository.getPeople().removeIf(person -> person.getId() == id) ? "Delete Successful" : "User was not found";
    }


    public void addPerson(Person person) {
        personRepository.getPeople().add(new Person(personRepository.getIdCounter().incrementAndGet(), person.getName(), person.getAge(), person.getGender()));
    }

    public void updatePersonById(Integer id, PersonUpdateRequest updatedPerson) {

        personRepository.getPeople().stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .ifPresent(person -> {
                    var index = personRepository.getPeople().indexOf(person);

                    if (updatedPerson.getName() != null && !updatedPerson.getName().isEmpty() && !updatedPerson.getName().equals(person.getName())) {
                        Person newPerson = new Person(person.getId(), updatedPerson.getName(), person.getAge(), person.getGender());
                        personRepository.getPeople().set(index, newPerson);
                    }
                });
    }
}
