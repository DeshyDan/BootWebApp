package com.tutorial.bootwebapp.person;


import com.tutorial.bootwebapp.SortingOrder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Person getPersonById(@PathVariable Integer id) {

        return personService.getPersonById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable Integer id) {
        personService.deletePersonById(id);
    }


    @PostMapping("")
    public void addPerson(@Valid @RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/{id}")
    public void updatePersonById(@PathVariable("id") Integer id, @Valid @RequestBody Person person) {

        personService.updatePersonById(id, person);
    }
}
