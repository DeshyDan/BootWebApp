package com.tutorial.bootwebapp.person;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotNull
    @Min(value = 16, message = "Age must be greater than 16")
    private Integer age;

    @NotNull(message = "Gender must not be be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    public Person() {
    }

    public Person(Integer id, String name, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty(message = "Name must not be empty") String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(@NotNull @Min(value = 16, message = "Age must be greater than 16") Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Gender must not be be null") Gender gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(age, person.age) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender);
    }
}
