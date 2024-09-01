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
    private final AnimalRepository animalRepository;
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

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
            animalDTOList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

    private boolean findAnimal(String id) {
        for (AnimalDTO animal : animalDTOList) {
            if (id.equalsIgnoreCase(animal.getId())) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity getAllAnimal() {
        List<AnimalDTO> animalsList = getAnimal();

        if(animalsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalsList);
    }


    public List<AnimalDTO> getAnimal() {
        Iterable<Animal> animalIterable = animalRepository.findAll();
        List<AnimalDTO> animals = new ArrayList<>();
        for(Animal anim : animalIterable) {
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(anim.getName() + "-" + anim.getBreed() + "-" + anim.getColor());
            animal.setName(anim.getPetName());
            animal.setWeight(anim.getWeight());
            animal.setLenght(anim.getLength());
            animal.setHeight(anim.getHeight());
            animals.add(animal);
        }
        return animals;
    }


    public ResponseEntity getAnimalById(String id) {
        Optional<Animal> animalOptional = animalRepository.findById(Long.valueOf(id));
        if(animalOptional.isPresent()) {
            Animal animalFound = animalOptional.get();
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(animalFound.getName() + "-" + animalFound.getBreed() + "-" + animalFound.getColor());
            animal.setWeight(animalFound.getWeight());
            animal.setLenght(animalFound.getLength());
            animal.setHeight(animalFound.getHeight());
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } else {
            String errorMessage = "Person with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }



    public AnimalDTO updateAnimal(String id, AnimalDTO animal){
        AnimalDTO ani = new AnimalDTO();
        int index = 0;
        for(AnimalDTO anim : animalDTOList){
            if(id.equalsIgnoreCase(anim.getId())){
                animalDTOList.set(index, animal);
                return animal;
            }
            index++;
        }
        return ani;
    }

    private int findIndex(String id){
        int indexFound = -1;
        int index = 0;
        for(AnimalDTO a : animalDTOList){
            if(id.equalsIgnoreCase(a.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deleteAnimal(String id) {
        String message = "Animal with id " + id;
        for (AnimalDTO ani : animalDTOList) {
            if (id.equalsIgnoreCase(ani.getId())) {
                animalDTOList.remove(ani);
                return message + " Removed successfully";
            }
        }
        return message + " not found";
    }
}
