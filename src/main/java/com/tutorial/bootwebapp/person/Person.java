package com.tutorial.bootwebapp.person;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Person {


    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    private String name;
    @Min(value = 16, message = "Age must be greater than 16")
    private Integer age;
    @NotNull(message = "Gender must not be be null")
    private Gender gender;


    public Person(Integer id, String name, Integer age, Gender gender) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
