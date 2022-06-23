package ik.ijse.dep8.note.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements SuperEntity {
    @Id
    private String id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Note> notes;
    @PrePersist
    private void beforePersist(){
        notes.forEach(note -> note.setUser(this));
    }
}
