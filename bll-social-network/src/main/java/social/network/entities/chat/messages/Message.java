package social.network.entities.chat.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;

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
    protected Set<Integer> usersDeletedMessage;
}