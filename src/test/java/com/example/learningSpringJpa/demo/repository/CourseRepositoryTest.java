package com.example.learningSpringJpa.demo.repository;

import com.example.learningSpringJpa.demo.Entity.Course;
import com.example.learningSpringJpa.demo.Repository.CourseJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import javax.swing.text.html.parser.Entity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CourseRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Test
    void findById_basic() {
        Optional<Course> byID = courseJpaRepository.findByID(10001L);
        assertTrue(byID.isPresent());
        assertEquals("SpringBoot", byID.get().getName());
    }

    @Test
    void deleteById_basic() {

        courseJpaRepository.deleteById(10001L);

        Optional<Course> course =
                courseJpaRepository.findByID(10001L);

        assertTrue(course.isEmpty());
    }

    @Test
    void save_basic() {
        Optional<Course> byID = courseJpaRepository.findByID(10001L);
        assertTrue(byID.isPresent());
        assertEquals("SpringBoot", byID.get().getName());

        byID.get().setName("React");

        courseJpaRepository.save(byID.get());

        Optional<Course> byID1 = courseJpaRepository.findByID(10001L);
        assertTrue(byID1.isPresent());
        assertEquals("React", byID1.get().getName());
    }

    @Test
    void playWithEM_basic() {
        courseJpaRepository.playWithEntityManager();
    }

}