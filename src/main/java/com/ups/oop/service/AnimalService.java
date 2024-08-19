package com.ups.oop.service;

import com.ups.oop.dto.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<Animal> AnimalList = new ArrayList<>();

    public ResponseEntity createAnimal(Animal animal) {
        String animalId = animal.getId();
        boolean wasFound = findAnimal (animalId);
        if(wasFound){
            String errorMessage = "Animal with id " + animalId + " already exist";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Animal with id " + animal.getId() + " already exist");
        } else {
            AnimalList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

    private boolean findAnimal(String id){
        for(Animal animal: AnimalList) {
            if (id.equalsIgnoreCase(animal.getId())) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity getAllAnimal() {
        if(AnimalList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(AnimalList);
    }

    public ResponseEntity getAnimalById(String id) {
        for(Animal ani : AnimalList){
            if(id.equalsIgnoreCase(ani.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(ani);
            }
        }
        String errorMessage = "animal with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public Animal updateAnimal(String id, Animal animal){
        Animal ani = new Animal();
        int index = 0;
        for(Animal anim : AnimalList){
            if(id.equalsIgnoreCase(anim.getId())){
                AnimalList.set(index, animal);
                return animal;
            }
            index++;
        }
        return ani;
    }

    private int findIndex(String id){
        int indexFound = -1;
        int index = 0;
        for(Animal a : AnimalList){
            if(id.equalsIgnoreCase(a.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deleteAnimal(String id) {
        String message = "animal with id " + id;
        for (Animal ani : AnimalList) {
            if (id.equalsIgnoreCase(ani.getId())) {
                AnimalList.remove(ani);
                return message + " removed successfully";
            }
        }
        return message + " not found";
    }
}