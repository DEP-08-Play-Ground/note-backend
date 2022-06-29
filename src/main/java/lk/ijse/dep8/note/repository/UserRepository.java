package lk.ijse.dep8.note.repository;

import lk.ijse.dep8.note.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User,String> {
    boolean existsUserByEmail(String email);
    Optional<User> findUserByEmail(String email);

//    @Query("SELECT u FROM lk.ijse.dep8.note.entity.User u")
//    @Query(value = "SELECT u FROM lk.ijse.dep8.note.entity.User u",nativeQuery = true)

//    List<User> findUsersByFullNameLike(String query);

//    @Query(value = "SELECT * FROM User WHERE email LIKE :email",nativeQuery = true)
//    List<User> findUserBySomething(@Param("email") String email);

//    @Query(value = "SELECT * FROM User WHERE email LIKE ?1",nativeQuery = true)
//    List<User> findUserBySomething(String email); //first one is the first parameter

//    @Query(value = "SELECT * FROM User WHERE email LIKE ?#{0}",nativeQuery = true)
//    List<User> findUserBySomething(String email); //first one is the first parameter

//    @Query(value = "SELECT * FROM User WHERE email LIKE ?#{0}",nativeQuery = true)
//    List<User> findUserBySomething(String email);

}
