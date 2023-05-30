package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatProfile;

import java.io.IOException;
import java.util.Optional;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class CreatePrivateChatAppRequest {
    private String name = "";
    private String description = "";
    private MultipartFile avatar;
    public PrivateChatProfile getPrivateChatProfile() throws IOException {
        return new PrivateChatProfile(
                new PrivateChatDescription(
                        name,
                        Optional.ofNullable(description)
                ),
                Optional.ofNullable(avatar == null || avatar.getBytes().length == 0 ? null: avatar.getBytes())
        );
    }

}
