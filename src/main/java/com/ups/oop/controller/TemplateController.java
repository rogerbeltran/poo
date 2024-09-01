package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final AnimalService animalService;


    @GetMapping("/template")
    public String getTemplate(Model model) {
        return "template";
    }

    public TemplateController(PersonService personService, AnimalService animalService) {
        this.personService = personService;
        this.animalService = animalService;
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "person/list";
    }

    @GetMapping("/animals")
    public String getAnimal(Model model) {
        model.addAttribute("animals", animalService.getAnimal());
        return "animal/list";
    }
}