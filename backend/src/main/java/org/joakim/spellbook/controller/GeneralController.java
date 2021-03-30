package org.joakim.spellbook.controller;

import org.joakim.spellbook.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeneralController {

    @Autowired
    SpellRepository repository;

    @GetMapping()
    public @ResponseBody String isAlive() {
        return "The book is open...";
    }

}
