package social.network.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.messages.MessageDTO;
import social.network.jpa.entities.ids.MessageEntityId;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageEntity {
    @EmbeddedId
    private MessageEntityId id;
    @Column(name = "chat_member_id")
    private int chatMemberId;
    @Column(name = "text")
    private String text;
    @Column(name = "images")
    private byte[] images;
    public MessageDTO getMessageDTO() {
        return new MessageDTO(id.getOrderId(), text, chatMemberId);
    }
}
