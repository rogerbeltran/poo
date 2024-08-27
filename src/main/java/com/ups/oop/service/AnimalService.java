package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.dto.Person;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Animal;
import com.ups.oop.repository.AnimalRepository;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private List<AnimalDTO> AnimalList = new ArrayList<>();
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public ResponseEntity createAnimal(AnimalDTO animal) {
        String animalId = animal.getId();
        boolean wasFound = findAnimal(animalId);
        if (wasFound) {
            String errorMessage = "Animal with id " + animalId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Animal with id " + animal.getId() + " already exists");
        } else {
            AnimalList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

    private boolean findAnimal(String id) {
        for (AnimalDTO animal : AnimalList) {
            if (id.equalsIgnoreCase(animal.getId())) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity getAllAnimal() {
        Iterable<Animal> animalIterable = animalRepository.findAll();
        List<AnimalDTO> animals = new ArrayList<>();
        for (Animal a : animalIterable) {
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(a.getName() + "-" + a.getBreed() + "-" + a.getColor());
            animal.setWeight(a.getWeight());
            animal.setLenght(a.getLenght());
            animal.setHeight(a.getHeight());
            animals.add(animal);
        }
        if (animals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    public ResponseEntity getAnimalById(String id) {
        Optional<Animal> animalOptional = animalRepository.findById(Long.valueOf(id));
        //Optional<Animal> animalOptional = animalRepository.findById(animalid);
        if (animalOptional.isPresent()) {
            //if record was found
            Animal animalFound = animalOptional.get();
            AnimalDTO animal = new AnimalDTO(animalFound.getId().toString(),
                    animalFound.getName() + "-" + animalFound.getBreed() + "-" + animalFound.getColor(),
                    animalFound.getWeight(), animalFound.getLenght(), animalFound.getHeight());
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } else {
            //if record wasn't found
            String errorMessage = "animal with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
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