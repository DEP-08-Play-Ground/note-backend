package ik.ijse.dep8.note.repository;

import ik.ijse.dep8.note.entity.SuperEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class CrudRepositoryImpl<T extends SuperEntity, ID extends Serializable> implements CrudRepository<T, ID> {

    private Class<T> entityClassObj;

    @PersistenceContext
    protected EntityManager entityManager;

    public CrudRepositoryImpl() {
        entityClassObj = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public T save(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(ID pk) {
        entityManager.remove(entityManager.getReference(entityClassObj,pk));
    }

    @Override
    public boolean existById(ID pk) {
        return findById(pk).isPresent();
    }

    @Override
    public Optional<T> findById(ID pk) {
        return Optional.of(entityManager.find(entityClassObj,pk));
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM "+entityClassObj.getName()+" e",entityClassObj).getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(e) FROM "+entityClassObj.getName()+" e",long.class).getSingleResult();
    }
}
