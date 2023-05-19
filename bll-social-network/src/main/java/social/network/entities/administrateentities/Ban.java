package social.network.entities.administrateentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ban {
    private int id;
    private int idUserBlocker;
    private String descriptions;
    private OffsetDateTime whenBanned;
}
