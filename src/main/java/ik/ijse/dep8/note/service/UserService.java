package ik.ijse.dep8.note.service;

import ik.ijse.dep8.note.dto.UserDTO;
import ik.ijse.dep8.note.service.exception.DuplicateEmailException;
import ik.ijse.dep8.note.service.exception.NotFoundException;

public interface UserService {
    UserDTO registerUser(UserDTO user) throws DuplicateEmailException;

    void updateUSer(UserDTO user) throws NotFoundException;

    UserDTO getUserInfo(String userId) throws NotFoundException;
    void deleteUser(String userId) throws NotFoundException;

}
