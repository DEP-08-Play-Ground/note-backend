package ik.ijse.dep8.note.repository.custom.impl;

import ik.ijse.dep8.note.entity.User;
import ik.ijse.dep8.note.repository.CrudRepositoryImpl;
import ik.ijse.dep8.note.repository.custom.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {

}
