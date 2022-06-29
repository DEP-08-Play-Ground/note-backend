package lk.ijse.dep8.note.service.util;

import lk.ijse.dep8.note.dto.NoteDTO;
import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOTransformer {
    private final ModelMapper mapper;

    public EntityDTOTransformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User getUserEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    public UserDTO getUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public NoteDTO getNoteDTO(Note note) {
        return mapper.typeMap(Note.class, NoteDTO.class).addMapping(note1 -> note1.getUser().getId(), NoteDTO::setUserId)
                .map(note);
    }

    public Note getNote(NoteDTO noteDTO) {
        return mapper.map(noteDTO, Note.class);
    }

}
