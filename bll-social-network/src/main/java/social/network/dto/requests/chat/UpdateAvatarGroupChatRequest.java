package social.network.dto.requests.chat;

import social.network.dto.modelsdto.GroupChatInfoDTO;
import lombok.Data;

import java.util.Optional;

@Data
public class UpdateAvatarGroupChatRequest {
    private int idUserSenderRequest;
    private int idChat;
    private Optional<byte[]> newAvatar;
}
