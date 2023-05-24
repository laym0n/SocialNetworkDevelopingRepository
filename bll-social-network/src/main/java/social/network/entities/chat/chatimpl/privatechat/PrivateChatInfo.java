package social.network.entities.chat.chatimpl.privatechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivateChatInfo {
    private String name;
    private Optional<byte[]> avatar;
}
