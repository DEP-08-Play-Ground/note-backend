package ik.ijse.dep8.note.repository.custom.impl;

import ik.ijse.dep8.note.repository.custom.QueryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QueryRepositoryImpl implements QueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

}
