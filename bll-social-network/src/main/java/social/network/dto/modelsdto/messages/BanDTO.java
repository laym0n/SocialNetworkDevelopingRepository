package social.network.dto.modelsdto.messages;

import social.network.entities.administrateentities.Ban;
import lombok.Data;

@Data
public class BanDTO {
    private int id;
    private int idBlocker;
    private String description;

    public Ban getBan() {
//        return new Ban(id, idBlocker, description);
        return null;
    }
}
