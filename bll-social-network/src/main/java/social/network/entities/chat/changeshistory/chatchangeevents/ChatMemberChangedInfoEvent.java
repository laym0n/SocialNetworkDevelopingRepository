package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;
import lombok.Data;

@Data
public class ChatMemberChangedInfoEvent extends ChatChangeEvent {
    private int idChatMember;

    public ChatMemberChangedInfoEvent(int idChatMember) {
        super(0, ChatChangeEventType.CHAT_MEMBER_CHANGED_INFO);
        this.idChatMember = idChatMember;
    }
}
