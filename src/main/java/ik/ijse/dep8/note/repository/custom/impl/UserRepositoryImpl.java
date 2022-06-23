package ik.ijse.dep8.note.repository.custom.impl;

import ik.ijse.dep8.note.entity.User;
import ik.ijse.dep8.note.repository.CrudRepositoryImpl;
import ik.ijse.dep8.note.repository.custom.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {

    @Override
    public boolean existsUserByEmail(String email) {
        return !entityManager.createQuery("SELECT u FROM ik.ijse.dep8.note.entity.User u WHERE u.email = :email").
                setParameter("email", email).getResultList().isEmpty();
    }
}
