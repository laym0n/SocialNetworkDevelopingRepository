package social.network.dto.modelsdto.messages;

import social.network.entities.chat.messages.SimpleMessage;

import java.time.OffsetDateTime;

public class SimpleMessageDTO extends MessageDTO {
    private int userId;
    private String text;
    private byte[][] images;
    private OffsetDateTime lastEditTime;

    @Override
    public SimpleMessage getMessage() {
        return new SimpleMessage();
    }
}
