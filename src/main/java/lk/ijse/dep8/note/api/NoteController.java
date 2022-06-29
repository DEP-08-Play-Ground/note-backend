package lk.ijse.dep8.note.api;

import lk.ijse.dep8.note.dto.NoteDTO;
import lk.ijse.dep8.note.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId:[A-Fa-f0-9\\-]{36}}/notes")
@CrossOrigin
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO addNote(@PathVariable String userId, @RequestBody @Validated NoteDTO noteDTO, Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (!userId.equals(noteDTO.getUserId())) throw new ResponseStatusException(HttpStatus.CONFLICT,"UserId Mismatch");
        return noteService.saveNote(noteDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(@PathVariable String userId){
        return noteService.getAllNotes(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{noteId:\\d+}")
    public void deleteNote(@PathVariable String userId,@PathVariable int noteId){
        noteService.deleteNote(userId,noteId);
    }
}
