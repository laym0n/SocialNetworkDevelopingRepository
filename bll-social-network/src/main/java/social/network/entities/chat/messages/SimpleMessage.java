package social.network.entities.chat.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage extends Message {
    private int userId;
    private String text;
    private List<byte[]> images;
    private OffsetDateTime lastEditTime;

}
