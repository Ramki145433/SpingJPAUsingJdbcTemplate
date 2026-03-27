package com.example.learningSpringJpa.demo.Entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String name;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Passport passport;

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    protected Student (){

    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
