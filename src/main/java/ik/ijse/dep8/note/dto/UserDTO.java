package ik.ijse.dep8.note.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {

    @Null(message = "Id can't be set")
    private String id;

    @Email(message = "Invalid Email")
    @NotNull(message = "Email can not be empty")
    private String email;

    @NotNull(message = "password can not be empty")
    @Max(value = 6, message = "Password should have at least 6 characters")
    private String password;

    @NotNull(message = "Full name can not be empty")
    @Pattern(regexp = "[A-Za-z ]+")
    private String fullName;
}
