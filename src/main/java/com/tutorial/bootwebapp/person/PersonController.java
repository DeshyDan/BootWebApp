package com.tutorial.bootwebapp.person;


import com.tutorial.bootwebapp.SortingOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/persons/")
public class PersonController {

    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
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

        return personService.getPersons(order, limit);
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) {

        return personService.getPersonById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable Integer id) {
        return personService.deletePersonById(id);
    }


    @PostMapping("")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/{id}")
    public void updatePersonById(@PathVariable("id") Integer id, @RequestBody PersonUpdateRequest updatedPerson) {

        personService.updatePersonById(id, updatedPerson);
    }
}
