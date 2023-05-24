package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatInfo;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateChatInfoDTO {
    private String name;
    private byte[] avatar;

    public PrivateChatInfo getPrivateChatInfo() {
        return new PrivateChatInfo(name, Optional.ofNullable(avatar));
    }
}
