package social.network.entities.chat.chatimpl.privatechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateChatProfile {
    private PrivateChatDescription description;
    private Optional<byte[]> avatar;
}
