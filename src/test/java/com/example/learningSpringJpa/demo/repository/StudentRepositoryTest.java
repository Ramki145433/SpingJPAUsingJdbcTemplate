package com.example.learningSpringJpa.demo.repository;

import com.example.learningSpringJpa.demo.Entity.Passport;
import com.example.learningSpringJpa.demo.Entity.Student;
import com.example.learningSpringJpa.demo.Repository.StudentJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentJpaRepository studentJpaRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void getStudentWithPassport() {
        // Here iam fetching the details for student but query by hibernate including passport details this is called Eager fetch
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student details -> {}", student);
        Passport passport = student.getPassport();
        logger.info("Passport details -> {}", passport);
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudentDetails() {
        Passport passport1 = entityManager.find(Passport.class, 30001L);
        logger.info("Passport details with id-> {}", passport1);
        Student student = passport1.getStudent();
        logger.info("Student details with above passport id -> {}", student);
        System.out.println("Test executed successfully");
    }
}
