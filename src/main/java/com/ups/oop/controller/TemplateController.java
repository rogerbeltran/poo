package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;

    public TemplateController(PersonService personService) {
        this.personService = personService;

    }

    @GetMapping("/templates")
    public String getTemplate(Model model) {
        return "templates";
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "person/list";
    }
}