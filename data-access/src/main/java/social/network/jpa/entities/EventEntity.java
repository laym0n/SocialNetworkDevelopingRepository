package social.network.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @EmbeddedId
    private PrimaryKeyEventEntity primaryKey;
    @Column(name = "order_id_message")
    private Integer messageOrderId;
    @Column(name = "chat_member_id")
    private Integer chatMemberId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type_id")
    private EventTypeEntity type;
}
