package ik.ijse.dep8.note.api;

import ik.ijse.dep8.note.dto.UserDTO;
import ik.ijse.dep8.note.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody @Valid UserDTO user) {
        userService.registerUser(user);
        return user;
    }

    @GetMapping(path = "/{userId:[A-Fa-f0-9-]{36}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserInfo(@PathVariable String userId) {
        return userService.getUserInfo(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable String userId, @RequestBody @Valid UserDTO user) {
        user.setId(userId);
        userService.updateUSer(user);

    }


}
