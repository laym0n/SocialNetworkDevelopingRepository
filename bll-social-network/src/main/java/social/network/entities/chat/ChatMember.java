package social.network.entities.chat;

import social.network.entities.chat.chatmemberinfo.ChatMemberInfo;
import lombok.Data;

import java.util.Set;

@Data
public class ChatMember {
    private int lastOrderIdOfCheckedMessage;
    private int orderIdOfMessageWhereStartReading;
    private ChatMemberInfo info;
    private boolean isBlocked = false;
    private Set<Integer> orderIdsDeletedMessages;
}
