package taskmanager.dto;

public record CreateTaskDTO(
        String title,
        String description,
        String deadline) {
}
