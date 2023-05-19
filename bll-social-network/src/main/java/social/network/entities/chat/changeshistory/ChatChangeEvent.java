package social.network.entities.chat.changeshistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ChatChangeEvent {
    protected int orderId;
    protected ChatChangeEventType eventType;
}
