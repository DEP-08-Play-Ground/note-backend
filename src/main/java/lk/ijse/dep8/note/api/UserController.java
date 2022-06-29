package lk.ijse.dep8.note.api;

import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody @Validated UserDTO user, Errors errors) {
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors().get(0).getDefaultMessage());
        }
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
