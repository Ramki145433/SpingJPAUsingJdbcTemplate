package com.example.learningSpringJpa.demo.Repository;

import com.example.learningSpringJpa.demo.Entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class CourseJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Course> findByID(Long id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }


    public void deleteById(Long id) {
        Course course = entityManager.find(Course.class, id);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    public void playWithEntityManager() {
        Course course = entityManager.find(Course.class, 10001L);
//        course.setName("Angular");
        /*
          FLUSH:
          Memory changes → Database immediately
          But transaction is NOT committed yet.
        */
//        entityManager.flush();
//        System.out.println(course.getName());
        /*
            CLEAR:
            Persistence Context → EMPTY
            All entities become detached.

            DETACH:
            Stops tracking a specific entity
        */
//        entityManager.clear();
//        course.setName("Angular");
//        Course course1 = entityManager.find(Course.class, 10001L);
//        System.out.println(course1.getName());

        /*
            REFRESH:
            Discard local changes
            Reload from DB
         */
        course.setName("Macro Services");
        entityManager.refresh(course);
        Course course1 = entityManager.find(Course.class, 10001L);
        System.out.println(course1.getName());

    }
}
