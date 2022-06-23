package ik.ijse.dep8.note.service.impl;

import ik.ijse.dep8.note.dto.UserDTO;
import ik.ijse.dep8.note.entity.User;
import ik.ijse.dep8.note.repository.custom.UserRepository;
import ik.ijse.dep8.note.service.UserService;
import ik.ijse.dep8.note.service.exception.DuplicateEmailException;
import ik.ijse.dep8.note.service.exception.NotFoundException;
import ik.ijse.dep8.note.service.util.EntityDTOTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EntityDTOTransformer transformer;

    public UserServiceImpl(UserRepository userRepository, EntityDTOTransformer transformer) {
        this.userRepository = userRepository;
        this.transformer = transformer;
    }

    @Override
    public UserDTO registerUser(UserDTO user) throws DuplicateEmailException {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new DuplicateEmailException("Email Already Exists");
        }
        return transformer.getUserDTO(userRepository.save(transformer.getUserEntity(user)));

    }

    @Override
    public void updateUSer(UserDTO user) throws NotFoundException {
        Optional<User> optUser = userRepository.findById(user.getEmail());
        if (!optUser.isPresent()) throw new NotFoundException("Invalid user Id");
        optUser.get().setFullName(user.getFullName());
        optUser.get().setPassword(user.getPassword());
    }

    @Override
    public UserDTO getUserInfo(String userId) throws NotFoundException {
        return userRepository.findById(userId).map(transformer::getUserDTO).orElseThrow(() -> new NotFoundException("Invalid User ID"));
    }

    @Override
    public void deleteUser(String userId) throws NotFoundException {
        if (userRepository.existById(userId)) throw new NotFoundException("Invalid User Id");
        userRepository.deleteById(userId);
    }
}
