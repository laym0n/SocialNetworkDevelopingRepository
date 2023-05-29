package social.network.entities.chat;

import social.network.entities.administrateentities.Ban;
import social.network.entities.chat.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    protected int id;
    protected List<Message> messages = new ArrayList<>();
    protected boolean isBlocked = false;
    protected boolean isActive = true;
    public List<Message> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }
}
