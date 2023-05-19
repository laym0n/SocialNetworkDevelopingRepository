package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatMemberLeavedEvent extends ChatChangeEvent {
    private int idChatMember;

    public ChatMemberLeavedEvent(int idChatMember) {
        super(0, ChatChangeEventType.CHAT_MEMBER_LEAVED);
        this.idChatMember = idChatMember;
    }
}
