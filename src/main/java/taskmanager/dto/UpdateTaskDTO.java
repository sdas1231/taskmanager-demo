package taskmanager.dto;

public record UpdateTaskDTO(
        String description,
        String deadline,
        Boolean completed) {
}
