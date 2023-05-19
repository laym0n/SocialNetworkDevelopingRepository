package social.network.dto.requests.chat;

import lombok.Data;

@Data
public class AddChatMemberRequest {
    private int idUserInviter;
    private int idNewMember;
    private int idChat;
}
