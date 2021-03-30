package org.joakim.spellbook.service;

import com.fasterxml.jackson.core.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.joakim.spellbook.entity.Spell;
import org.joakim.spellbook.repository.SpellRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class SpellService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SpellRepository repository;

    public Iterable<Spell> getAllSpells() {
        return repository.findAll();
    }

    public Optional<Spell> getSpellById(Long id) {
        return repository.findById(id);
    }

    public Optional<Spell> getSpellByName(String name) {
        Spell spell;
        String name_formatted = name.replace("-", " ");

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Spell> cq = builder.createQuery(Spell.class);
        Root<Spell> root = cq.from(Spell.class);
        List<Spell> resultList = entityManager.createQuery(
                cq.select(root).where(builder.equal(root.get("name"), name_formatted))).getResultList();
        if (resultList.isEmpty()) {
            spell = null;
        } else {
            spell = resultList.get(0);
        }
        return Optional.ofNullable(spell);
    }

    public Iterable<Spell> getSpellsBySchool(String school) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Spell> cq = builder.createQuery(Spell.class);
        Root<Spell> root = cq.from(Spell.class);
        return entityManager.createQuery(
                cq.select(root).where(builder.equal(root.get("school"), school))).getResultList();
    }

    public void insertFromFile() {
        String file = "backend/src/main/java/org/joakim/spellbook/data/insert2.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skips headers
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(";", -1);
                Spell s = new Spell(lineArr);
                repository.save(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
