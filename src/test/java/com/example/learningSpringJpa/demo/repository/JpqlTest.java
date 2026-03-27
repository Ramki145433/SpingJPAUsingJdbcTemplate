package com.example.learningSpringJpa.demo.repository;

import com.example.learningSpringJpa.demo.Entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JpqlTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void jpql_basic() {
        Query selectCFromCourseC = entityManager.createQuery("Select c From Course c");
        List resultList = selectCFromCourseC.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> selectCFromCourseC = entityManager.createQuery("Select c From Course c", Course.class);
        List<Course> resultList = selectCFromCourseC.getResultList();
        logger.info("Select c From Course c -> {}",
                resultList);
    }

}
