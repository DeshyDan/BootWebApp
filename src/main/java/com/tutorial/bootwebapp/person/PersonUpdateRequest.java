package com.tutorial.bootwebapp.person;


public class PersonUpdateRequest {

    private String name;
    private Integer age;

    public PersonUpdateRequest() {

    }

    public PersonUpdateRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}