package taskmanager.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taskmanager.dto.CreateNoteDTO;
import taskmanager.dto.CreateNoteResponseDTO;
import taskmanager.dto.NoteResponseDTO;
import taskmanager.entity.NoteEntity;
import taskmanager.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = noteService.getNotesForTask(taskId);
        if (Objects.isNull(notes))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDTO body) {
        var note = noteService.addNoteForTask(taskId, body.title(), body.body());
        if (Objects.isNull(note))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, new NoteResponseDTO(
            note.getId(), note.getTitle(), note.getBody())));
    }

}
