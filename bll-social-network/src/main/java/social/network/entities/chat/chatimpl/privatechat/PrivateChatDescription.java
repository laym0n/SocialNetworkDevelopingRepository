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
public class PrivateChatDescription {
    private String name;
    private Optional<String> description;
}
