package social.network.dto.requests.chat;

import lombok.Data;

@Data
public class AddChatMemberRequest {
    private int idUserSenderRequest;
    private int idUserNewMember;
    private int idChat;
}
