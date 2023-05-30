package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDescriptionDTO {
    private int chatId;
    private String name;
    private Optional<byte[]> avatar;
}
