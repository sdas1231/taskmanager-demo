package taskmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanager.entity.NoteEntity;
import taskmanager.entity.TaskEntity;

@Service
public class NoteService {

    @Autowired
    private TaskService taskService;

    private Map<Integer, TaskNotesHolder> taskNoteHolders = new HashMap<>();

    class TaskNotesHolder {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);

        if (Objects.isNull(task)) return null;

        if (taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }
        return taskNoteHolders.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body) {
        TaskEntity task = taskService.getTaskById(taskId);

        if (Objects.isNull(task)) return null;

        if (Objects.isNull(taskNoteHolders.get(taskId))) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity(taskNotesHolder.noteId, title, body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }
}
