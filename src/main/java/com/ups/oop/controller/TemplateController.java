package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final AnimalService animalService;
    private final BookService  bookService;


    public TemplateController(PersonService personService, AnimalService animalService, BookService bookService) {
        this.personService = personService;
        this.animalService = animalService;
        this.bookService = bookService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model) {
        return "template";
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
    @GetMapping("/books")
    public String getBookAndAuthor(Model model){
        model.addAttribute("books", bookService.getBookAndAuthor());
        return "book/list";
    }
}