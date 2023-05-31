package social.network.entities.chat.changeshistory.chatchangeevents;

import lombok.Data;
import social.network.entities.chat.changeshistory.ChatChangeEvent;

@Data
public class ChatMemberChangedInfoEvent extends ChatChangeEvent {
    private int idChatMember;

    public ChatMemberChangedInfoEvent(int idChatMember) {
        super(0);
        this.idChatMember = idChatMember;
    }
}
