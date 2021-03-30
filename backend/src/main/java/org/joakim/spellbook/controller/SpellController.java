package org.joakim.spellbook.controller;

import org.joakim.spellbook.entity.Spell;
import org.joakim.spellbook.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("spell")
public class SpellController {

    @Autowired
    SpellService service;

    @GetMapping()
    @ResponseBody
    public Iterable<Spell> getAllSpells() {
        return service.getAllSpells();
    }


    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Spell> getSpellById(@PathVariable final Long id) {
        Optional<Spell> spell = service.getSpellById(id);

        return spell.map(value
                -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("name/{name}")
    @ResponseBody
    public ResponseEntity<Spell> getSpellByName(@PathVariable final String name) {
        Optional<Spell> spell = service.getSpellByName(name);

        return spell.map(value
                -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("school/{school}")
    @ResponseBody
    public ResponseEntity<Iterable<Spell>> getSpellsBySchool(@PathVariable final String school) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSpellsBySchool(school));

    }

    @PostMapping("insert")
    public void insertFromFile() {
        service.insertFromFile();
    }

}
