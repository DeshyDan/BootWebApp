package com.tutorial.bootwebapp.person;

import com.tutorial.bootwebapp.SortingOrder;
import com.tutorial.bootwebapp.exception.BadRequestException;
import com.tutorial.bootwebapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(SortingOrder order, Integer limit) {

        if (order == SortingOrder.ASC) {
            return personRepository.getPeople().stream().limit(limit).sorted(Comparator.comparing(Person::getId)).collect(Collectors.toList());

        }

        return personRepository.getPeople().stream().limit(limit).sorted(Comparator.comparing(Person::getId).reversed()).collect(Collectors.toList());
    }


    public Person getPersonById(Integer id) {

        return personRepository.getPeople().stream().filter(person -> person.getId()
                        .equals(id)).
                findFirst().
                orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    }

    public void deletePersonById(Integer id) {
        Person person = personRepository.getPeople().stream().filter(oldPerson -> oldPerson.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));

        personRepository.getPeople().remove(person);

    }


    public void addPerson(Person person) {
        System.out.println(person);
        if (personRepository.isPersonAvailable(person.getName())) {
            throw new BadRequestException("Name " + person.getName() + "is already taken");
        }
        personRepository.getPeople()
                .add(new Person(personRepository.getIdCounter().incrementAndGet(),
                        person.getName(),
                        person.getAge(),
                        person.getGender()));
    }

    public void updatePersonById(Integer id, PersonUpdateRequest updatedPerson) {

        Person person = personRepository.getPeople().stream()
                .filter(oldPerson -> oldPerson.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));

        var index = personRepository.getPeople().indexOf(person);

        if (updatedPerson.getName() != null && !updatedPerson.getName().isEmpty() && !updatedPerson.getName().equals(person.getName())) {
            Person newPerson = new Person(person.getId(),
                    updatedPerson.getName(),
                    person.getAge(),
                    person.getGender());
            personRepository.getPeople().set(index, newPerson);
        }

    }
}
