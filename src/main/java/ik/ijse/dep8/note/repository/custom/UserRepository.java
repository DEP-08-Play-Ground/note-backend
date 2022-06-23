package ik.ijse.dep8.note.repository.custom;

import ik.ijse.dep8.note.entity.User;
import ik.ijse.dep8.note.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    boolean existsUserByEmail(String email);
}
