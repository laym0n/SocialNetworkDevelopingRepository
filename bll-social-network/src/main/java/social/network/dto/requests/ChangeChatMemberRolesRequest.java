package social.network.dto.requests;

import lombok.Data;

@Data
public class ChangeChatMemberRolesRequest {
    private int idUserSenderRequest;
    private int chatId;
    private int idChatMemberForChangeRoles;
    private String[] newRoles;
}
