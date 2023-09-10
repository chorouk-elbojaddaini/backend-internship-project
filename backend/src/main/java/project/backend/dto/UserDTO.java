package project.backend.dto;

import jakarta.persistence.Lob;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String job;

    @Lob
    private byte[] image;


}
