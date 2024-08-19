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
        p1.setPersonId("0958177933");
        p1.setName("Bryan");
        p1.setLastName("Plaza");
        p1.setAge(28);

        Person p2 = new Person();
        p2.setPersonId("0958177934");
        p2.setName("Juan");
        p2.setLastName("Pueblo");
        p2.setAge(39);

        personRepository.save(p1);
        personRepository.save(p2);

        System.out.println("--------------Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());

    }

}