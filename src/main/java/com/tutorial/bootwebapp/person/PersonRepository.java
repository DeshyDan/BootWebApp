package com.tutorial.bootwebapp.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PersonRepository extends JpaRepository<Person, Integer> {


    boolean existsByName(String name);
}
