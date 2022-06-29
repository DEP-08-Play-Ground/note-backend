package lk.ijse.dep8.note.service.impl;

import lk.ijse.dep8.note.dto.NoteDTO;
import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.NoteRepository;
import lk.ijse.dep8.note.repository.UserRepository;
import lk.ijse.dep8.note.service.NoteService;
import lk.ijse.dep8.note.service.exception.NotFoundException;
import lk.ijse.dep8.note.service.exception.UnauthorizedAccessException;
import lk.ijse.dep8.note.service.util.EntityDTOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final EntityDTOTransformer transformer;
    private final UserRepository userRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, EntityDTOTransformer transformer, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.transformer = transformer;
        this.userRepository = userRepository;
    }

    @Override
    public NoteDTO saveNote(NoteDTO note) throws NotFoundException {
        Note noteEntity = transformer.getNote(note);
        noteEntity.setUser(getUser(note.getUserId()));
        return transformer.getNoteDTO(noteRepository.save(noteEntity));
    }

    @Override
    public void deleteNote(String userId, int noteId) throws NotFoundException {
        Optional<Note> optNote = noteRepository.findById(noteId);
        if (!optNote.isPresent()) throw new NotFoundException("Invalid Note ID");
        if (getUser(userId)!=optNote.get().getUser())
            throw new UnauthorizedAccessException("User isn't Authorized to delete this note");
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<NoteDTO> getAllNotes(String userId) {
        return noteRepository.findAllNotesByUser(getUser(userId)).stream()
                .map(transformer::getNoteDTO).collect(Collectors.toList());
    }

    private User getUser(String userId){
        return userRepository.findById(userId).orElseThrow(()->new NotFoundException("Invalid User Id"));
    }
}
