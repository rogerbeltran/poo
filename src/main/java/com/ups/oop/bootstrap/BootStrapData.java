package com.ups.oop.bootstrap;


import com.ups.oop.dto.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        Person p1 = new Person();
        p1.setPersonId("0998377188");
        p1.setName("Luis");
        p1.setLastName("García");
        p1.setAge(38);

        Person p2 = new Person();
        p2.setPersonId("0987311633");
        p2.setName("Sandra");
        p2.setLastName("Olivares");
        p2.setAge(42);

        personRepository.save(p1);
        personRepository.save(p2);

        System.out.println("--------------Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());

    }

}