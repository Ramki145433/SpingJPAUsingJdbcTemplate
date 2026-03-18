package com.example.learningSpringJpa.demo;

import com.example.learningSpringJpa.demo.Entity.Person;
import com.example.learningSpringJpa.demo.Repository.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

@SpringBootApplication
public class LearningSpringJpaApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringJpaApplication.class, args);
	}

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Find by id for 10006-> {}", personJpaRepository.findById(10006));
        logger.info("Updating 10006 id -> {}", personJpaRepository.update( new Person(10006, "Srinu", "Srikakulam", new Date())));
        logger.info("Inserting new person -> {}", personJpaRepository.insert(new Person("Manu", "America", new Date())));
        personJpaRepository.deleteById(10008);
        logger.info("All persons from the Persons table -> {}", personJpaRepository.findAll());
    }
}