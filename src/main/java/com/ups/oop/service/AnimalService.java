package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<AnimalDTO> AnimalList = new ArrayList<>();

    public ResponseEntity createAnimal(AnimalDTO animal) {
        String animalId = animal.getId();
        boolean wasFound = findAnimal (animalId);
        if(wasFound){
            String errorMessage = "Animal with id " + animalId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Animal with id " + animal.getId() + " already exists");
        } else {
            AnimalList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

    private boolean findAnimal(String id){
        for(AnimalDTO animal: AnimalList) {
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
        for(AnimalDTO ani : AnimalList){
            if(id.equalsIgnoreCase(ani.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(ani);
            }
        }
        String errorMessage = "animal with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public AnimalDTO updateAnimal(String id, AnimalDTO animal){
        AnimalDTO ani = new AnimalDTO();
        int index = 0;
        for(AnimalDTO anim : AnimalList){
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
        for(AnimalDTO a : AnimalList){
            if(id.equalsIgnoreCase(a.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deleteAnimal(String id) {
        String message = "Animal with id " + id;
        for (AnimalDTO ani : AnimalList) {
            if (id.equalsIgnoreCase(ani.getId())) {
                AnimalList.remove(ani);
                return message + " Removed successfully";
            }
        }
        return message + " not found";
    }
}