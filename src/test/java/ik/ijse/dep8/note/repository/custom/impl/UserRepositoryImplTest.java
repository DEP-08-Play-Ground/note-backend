package ik.ijse.dep8.note.repository.custom.impl;

import ik.ijse.dep8.note.config.WebAppConfig;
import ik.ijse.dep8.note.config.WebRootConfig;
import ik.ijse.dep8.note.entity.User;
import ik.ijse.dep8.note.repository.custom.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringJUnitConfig({WebRootConfig.class, WebAppConfig.class})
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        //given
        User user = new User(UUID.randomUUID().toString(), "dulanga@ijse.lk", "dulanga", "Dulanga");
        //when
        User save = userRepository.save(user);
        //then
        assertEquals(user, save);
    }

    @Test
    void deleteById() {
        User user = new User(UUID.randomUUID().toString(), "dulanga@ijse.lk", "dulanga", "Dulanga");
        userRepository.save(user);

        userRepository.deleteById(user.getId());

        assertFalse(userRepository.existById(user.getId()));
    }

    @Test
    void findAll() {
        User dulanga = new User(UUID.randomUUID().toString(), "dulanga@ijse.lk", "dulanga", "Dulanga");
        User lahiru = new User(UUID.randomUUID().toString(), "lahiru@ijse.lk", "lahitu", "Lahiru");

        User savedDulanga = userRepository.save(dulanga);
        User savedLahiru = userRepository.save(lahiru);

        List<User> all = userRepository.findAll();
        assertTrue(all.size() == 2);

    }


    @Test
    void count() {
        User dulanga = new User(UUID.randomUUID().toString(), "dulanga@ijse.lk", "dulanga", "Dulanga");
        User lahiru = new User(UUID.randomUUID().toString(), "lahiru@ijse.lk", "lahitu", "Lahiru");

        userRepository.save(dulanga);
        userRepository.save(lahiru);

        long count = userRepository.count();
        assertTrue(count == 2);
    }
}