package social.network.dto.modelsdto.messages;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import social.network.entities.chat.messages.Message;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    protected int orderIdMessage;
    protected String text;
    protected int userIdOwner;
}
