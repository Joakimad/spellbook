package org.joakim.spellbook.entity;

import javax.persistence.*;

@Entity
public class Spell {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // General
    @Column(unique = true)
    private final String name;
    private final int level;
    private final String school;

    // Info
    private final Boolean ritual;
    private final String castTime;
    private final String spellRange;
    private final Boolean concentration;
    private final String duration;

    // Descriptions
    @Column(columnDefinition="varchar(1000)")
    private final String description;
    @Column(columnDefinition="varchar(1000)")
    private final String higherLevels;
    private final String source;

    public Spell() {
        this.name = "";
        this.level = -1;
        this.school = "";
        this.ritual = false;
        this.castTime = "";
        this.spellRange = "";
        this.concentration = false;
        this.duration = "";
        this.description = "";
        this.higherLevels = "";
        this.source = "";
    }

    public Spell(String name, int level, String school, Boolean ritual, String castTime, String spellRange, Boolean concentration, String duration, String description, String higherLevels, String source) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.ritual = ritual;
        this.castTime = castTime;
        this.spellRange = spellRange;
        this.concentration = concentration;
        this.duration = duration;
        this.description = description;
        this.higherLevels = higherLevels;
        this.source = source;
    }

    public Spell(String[] params) {
        this.name = params[0];
        this.level = Integer.parseInt(params[1]);
        this.school = params[2];
        this.ritual = Boolean.parseBoolean(params[3]);
        this.castTime = params[4];
        this.spellRange = params[5];
        this.concentration = Boolean.parseBoolean(params[6]);
        this.duration = params[7];
        this.description = params[8];
        this.higherLevels = params[9];
        this.source = params[10];
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", school='" + school + '\'' +
                ", ritual=" + ritual +
                ", castTime='" + castTime + '\'' +
                ", spellRange='" + spellRange + '\'' +
                ", concentration=" + concentration +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", atHigherLevels='" + higherLevels + '\'' +
                ", source='" + source + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getSchool() {
        return school;
    }

    public Boolean getRitual() {
        return ritual;
    }

    public String getCastTime() {
        return castTime;
    }

    public String getSpellRange() {
        return spellRange;
    }

    public Boolean getConcentration() {
        return concentration;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getHigherLevels() {
        return higherLevels;
    }

    public String getSource() {
        return source;
    }
}