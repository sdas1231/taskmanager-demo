package taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.dto.CreateTaskDTO;
import taskmanager.dto.UpdateTaskDTO;
import taskmanager.entity.TaskEntity;
import taskmanager.service.TaskService;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        if (Objects.isNull(tasks) || tasks.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        return ResponseEntity.ok(taskService.addTask(body.title(), body.description(), body.deadline()));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("taskId") Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        return ResponseEntity.ok(taskService.updateTask(id, body.description(), body.deadline(), body.completed()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
