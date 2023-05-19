package social.network.dto.modelsdto.messages;

import social.network.entities.chat.messages.Message;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public abstract class MessageDTO {
    protected int chatId;
    protected int orderIdOfChangeEvent;
    protected OffsetDateTime createdAt;
    protected String messageType;

    public abstract Message getMessage();
}
