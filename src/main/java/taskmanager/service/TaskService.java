package taskmanager.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import taskmanager.entity.TaskEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class TaskService {
    private List<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(final String title, final String description, final String deadline)
            throws ParseException {
        TaskEntity task = new TaskEntity(taskId,
                title, description, deadlineDateFormat.parse(deadline), false, Collections.emptyList());
        taskId++;
        tasks.add(task);
        return task;
    }

    public List<TaskEntity> getTasks() {
        if (tasks.isEmpty()) throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "entity not found");
        return tasks;
    }

    public TaskEntity getTaskById(final int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"));
    }

    public TaskEntity updateTask(final int id, final String description, final String deadline, final Boolean completed)
            throws ParseException {
        TaskEntity task = getTaskById(id);
        if (Objects.isNull(task))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found");

        if (description != null) {
            task.setDescription(description);
        }
        if (deadline != null) {
            task.setDeadline(deadlineDateFormat.parse(deadline));
        }
        if (completed != null) {
            task.setCompleted(completed);
        }

        return task;
    }

    public void deleteTask(final int id) {
        TaskEntity task = getTaskById(id);
        if (Objects.isNull(task))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found");

        tasks = tasks.stream().filter(t -> t.getId() != id).collect(Collectors.toList());

    }
}
