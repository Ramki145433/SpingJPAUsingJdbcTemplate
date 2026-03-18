package com.example.learningSpringJpa.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.util.Date;

@Entity
@NamedQuery(name = "find_all_persons", query = "select p from Person p")
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String location;
    private Date dateOfBirth;

    public Person(){}

    public Person(int id, String name, String location, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String name, String location, Date dateOfBirth) {
        this.name = name;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "\n Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
