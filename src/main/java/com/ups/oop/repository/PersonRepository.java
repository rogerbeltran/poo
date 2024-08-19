package com.ups.oop.repository;


import com.ups.oop.dto.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}