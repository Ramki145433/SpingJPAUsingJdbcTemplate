package com.example.learningSpringJpa.demo.Repository;

import com.example.learningSpringJpa.demo.Entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        person.setName("Sharma");
        return entityManager.merge(person);
    }

    public Person insert (Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

    public List<Person> findAll() {
        TypedQuery<Person> findAllPersons = entityManager.createNamedQuery("find_all_persons", Person.class);
        return findAllPersons.getResultList();
    }
}
