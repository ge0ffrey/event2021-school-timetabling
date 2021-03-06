package org.acme.schooltimetabling.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    // ************************************************************************
    // Constructors
    // ************************************************************************

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ************************************************************************
    // toString
    // ************************************************************************

    @Override
    public String toString() {
        return name;
    }

}
