package lk.ijse.dep8.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteDTO implements Serializable {
    @Null
    private Integer id;
    @NotBlank(message = "Text can not be empty")
    private String text;
    @Pattern(regexp = "[A-Za-z0-9\\-]{36}",message = "Invalid User Id")
    private String userId;
}
