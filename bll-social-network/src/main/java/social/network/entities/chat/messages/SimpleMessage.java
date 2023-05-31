package social.network.entities.chat.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage extends Message {
    private String text;
    private List<byte[]> images;

}
