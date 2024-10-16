package taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteEntity {
    private int id;
    private String title;
    private String body;
}
