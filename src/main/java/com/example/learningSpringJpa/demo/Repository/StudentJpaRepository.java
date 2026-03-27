package com.example.learningSpringJpa.demo.Repository;

import com.example.learningSpringJpa.demo.Entity.Passport;
import com.example.learningSpringJpa.demo.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void saveStudentWithPassport() {
        Passport passport = new Passport("S1234");
        Student student = new Student("Sushmita");

        // Establish relationship from both ends
        student.setPassport(passport);
        passport.setStudent(student);

        //Only with one persist you can save both tables using cascade
        entityManager.persist(student);
    }

}
