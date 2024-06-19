package com.tutorial.bootwebapp.person;

import com.tutorial.bootwebapp.SortingOrder;
import com.tutorial.bootwebapp.exception.BadRequestException;
import com.tutorial.bootwebapp.exception.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final FakePersonRepository fakePersonRepository;
    private final PersonRepository personRepository;

    public PersonService(FakePersonRepository fakePersonRepository, PersonRepository personRepository) {
        this.fakePersonRepository = fakePersonRepository;
        this.personRepository = personRepository;
    }

    //    public List<Person> getPersons(SortingOrder order, Integer limit) {
    //
    //        if (order == SortingOrder.ASC) {
    //            return fakePersonRepository.getPeople().stream().limit(limit).sorted(Comparator.comparing(Person::getId)).collect(Collectors.toList());
    //
    //        }
    //
    //        return fakePersonRepository.getPeople().stream().limit(limit).sorted(Comparator.comparing(Person::getId).reversed()).collect(Collectors.toList());
    //    }

    public List<Person> getPersons(SortingOrder order, Integer limit) {
        return personRepository.findAll(
                Sort.by(
                        Sort.Direction.valueOf(order.name()),
                        "id"));
    }


    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    }
    //    public Person getPersonById(Integer id) {
    //
    //        return fakePersonRepository.getPeople().stream().filter(person -> person.getId()
    //                        .equals(id)).
    //                findFirst().
    //                orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    //    }

    //    public void deletePersonById(Integer id) {
    //        Person person = fakePersonRepository.getPeople().stream().filter(oldPerson -> oldPerson.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    //
    //        fakePersonRepository.getPeople().remove(person);
    //
    //    }

    public void deletePersonById(Integer id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Person with id: " + id + " does not exist");
        }
    }


    public void addPerson(Person person) {

        if (personRepository.existsByName(person.getName()))

            throw new BadRequestException("Name " + person.getName() + "is already taken");

        //       taken fakePersonRepository.getPeople().add(new Person(fakePersonRepository.getIdCounter().incrementAndGet(), person.getName(), person.getAge(), person.getGender()));

        personRepository.save(person);
    }

    //    public void updatePersonById(Integer id, NewPersonRequest updatedPerson) {
    //
    //        Person person = fakePersonRepository.getPeople().stream().filter(oldPerson -> oldPerson.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    //
    //        var index = fakePersonRepository.getPeople().indexOf(person);
    //
    //        if (updatedPerson.getName() != null && !updatedPerson.getName().isEmpty() && !updatedPerson.getName().equals(person.getName())) {
    //            Person newPerson = new Person(person.getId(), updatedPerson.getName(), person.getAge(), person.getGender());
    //            fakePersonRepository.getPeople().set(index, newPerson);
    //        }
    //
    //    }
    //
    public void updatePersonById(Integer id, Person personRequest) {

        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
        person.setAge(personRequest.getAge());
        person.setName(personRequest.getName());
        person.setGender(personRequest.getGender());

        personRepository.save(person);
    }

}
