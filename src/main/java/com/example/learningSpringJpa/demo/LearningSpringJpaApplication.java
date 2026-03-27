package com.example.learningSpringJpa.demo;

import com.example.learningSpringJpa.demo.Entity.Course;
import com.example.learningSpringJpa.demo.Entity.Person;
import com.example.learningSpringJpa.demo.Repository.CourseJpaRepository;
import com.example.learningSpringJpa.demo.Repository.PersonJpaRepository;
import com.example.learningSpringJpa.demo.Repository.StudentJpaRepository;
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

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    StudentJpaRepository studentJpaRepository;

    @Override
    public void run(String... args) throws Exception {
//        logger.info("Find by id for 10006-> {}", personJpaRepository.findById(10006));
//        logger.info("Updating 10006 id -> {}", personJpaRepository.update( new Person(10006, "Srinu", "Srikakulam", new Date())));
//        logger.info("Inserting new person -> {}", personJpaRepository.insert(new Person("Manu", "America", new Date())));
//        personJpaRepository.deleteById(10008);
//        logger.info("All persons from the Persons table -> {}", personJpaRepository.findAll());
//
        // Course Table
        logger.info("Find by id for 10001 -> {}", courseJpaRepository.findByID(10001L));
//        courseJpaRepository.playWithEntityManager();
//        courseJpaRepository.deleteById(10001L);
//        logger.info("Inserting a course object -> {}", courseJpaRepository.save(new Course("Javascript in 30 days")));
//        logger.info("Updating existing course object -> {}", courseJpaRepository.save(new Course(10001L, "React")));

        /*
            When you trying update row with null for name column, you will face this below error,
            because we set nullable = false for name column in Course entity
            ERROR: not-null property references a null or transient value for entity com.example.learningSpringJpa.demo.Entity.Course.name
         */
//        logger.info("Updating existing course object -> {}", courseJpaRepository.save(new Course(10001L, null)));

        studentJpaRepository.saveStudentWithPassport();
    }
}