package social.network.entities.chat.messages;

import social.network.entities.administrateentities.Ban;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Message {
    protected int orderId;
    protected int chatId;
    protected OffsetDateTime createdAt;
    protected Optional<OffsetDateTime> editedAt;
    protected boolean isBlocked;
    protected Optional<Integer> idChatMemberOwner;
}