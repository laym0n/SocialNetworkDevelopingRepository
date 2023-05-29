package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAvatarPrivateChatRequest {
    private int idUserSenderRequest;
    private int chatId;
    private Optional<byte[]> newAvatar;
}
