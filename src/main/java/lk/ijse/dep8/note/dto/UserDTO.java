package lk.ijse.dep8.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
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
    @Length(min = 6, message = "Password should have at least 6 characters")
    private String password;

    @NotNull(message = "Full name can not be empty")
    @Pattern(regexp = "[A-Za-z ]+")
    private String fullName;
}
