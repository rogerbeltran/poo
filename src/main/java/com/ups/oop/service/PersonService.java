package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<PersonDTO> personList = new ArrayList<>();

    public ResponseEntity createPerson(PersonDTO person) {
        String personId = person.getId();
        boolean wasFound = findPerson(personId);
        if(wasFound){
            String errorMessage = "Person with id " + personId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Person with id " + person.getId() + " already exists");
        } else {
            personList.add(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }

    private boolean findPerson(String id){
        for(PersonDTO person: personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity getAllPeople() {
        if(personList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id) {
        for(PersonDTO per : personList){
            if(id.equalsIgnoreCase(per.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }
        String errorMessage = "person with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public PersonDTO updatePerson(String id, PersonDTO person){
        PersonDTO per = new PersonDTO();
        int index = 0;
        for(PersonDTO pers : personList){
            if(id.equalsIgnoreCase(pers.getId())){
                personList.set(index, person);
                return person;
            }
            index++;
        }
        return per;
    }

    private int findIndex(String id){
        int indexFound = -1;
        int index = 0;
        for(PersonDTO p : personList){
            if(id.equalsIgnoreCase(p.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deletePersonById(String id) {
        String message = "Person with id" + id;
        for (PersonDTO per : personList) {
            if (id.equalsIgnoreCase(per.getId())) {
                personList.remove(per);
                return message + " Removed successfully";
            }
        }
        return message + " not found";
    }
}