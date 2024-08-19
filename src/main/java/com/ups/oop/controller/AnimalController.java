package com.ups.oop.controller;

import com.ups.oop.dto.Animal;
import com.ups.oop.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController( AnimalService animalService){
        this.animalService =  animalService;
    }

    @PostMapping("/create-animal")
    public ResponseEntity createAnimal(@RequestBody Animal animal) {
        return this.animalService.createAnimal(animal);
    }

    @GetMapping("/get-all-animal")
    public ResponseEntity getAllAnimal(){
        return this.animalService.getAllAnimal();
    }

    @GetMapping("/get-animal")
    public ResponseEntity getAnimalById(@RequestParam String id){
        return this.animalService.getAnimalById(id);
    }

    @PutMapping("/update-animal")
    public Animal updateAnimal(@RequestParam String id, @RequestBody Animal animal){
        return this.animalService.updateAnimal(id, animal);
    }

    @DeleteMapping("/remove-animal")
    public String deleteAnimal(@RequestParam String id){
        return this.animalService.deleteAnimal(id);
    }
}