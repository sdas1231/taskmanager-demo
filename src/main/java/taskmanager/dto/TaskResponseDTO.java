package taskmanager.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record TaskResponseDTO(
        int id,
        String title,
        String description,
        Date deadline,
        boolean completed,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<NoteResponseDTO> notes) {
}
