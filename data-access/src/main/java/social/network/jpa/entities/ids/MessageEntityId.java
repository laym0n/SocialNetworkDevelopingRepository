package social.network.jpa.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntityId implements Serializable {
    @Column(name = "chat_id", nullable = false)
    private Integer chatId;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;
}
