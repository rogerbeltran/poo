package com.ups.oop.bootstrap;


import com.ups.oop.dto.Person;
import com.ups.oop.entity.Animal;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;


    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository, AnimalRepository animalRepository1){
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
    }


    @Override
    public void run(String... args) throws Exception {
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
        p2.setAge(39);
      
        personRepository.save(p1);
        personRepository.save(p2);

        Animal a1 = new Animal();
        a1.setName("Lady");
        a1.setColor("Brown");
        a1.setLenght(1.40);
        a1.setBreed("Golden Retriever");
        a1.setHeight(0.60);
        a1.setWeight(12);

        Animal a2 = new Animal();
        a2.setName("Max");
        a2.setColor("White");
        a2.setLenght(0.50);
        a2.setBreed("Puddle");
        a2.setHeight(0.45);
        a2.setWeight(6);

        animalRepository.save(a1);
        animalRepository.save(a2);

        System.out.println("--------------Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
        System.out.println("Number of Animals: " + animalRepository.count());
    }
}