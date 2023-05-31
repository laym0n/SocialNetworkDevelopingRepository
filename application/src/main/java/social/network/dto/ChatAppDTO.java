package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatAppDTO {
    public static ResourceLoader resourceLoader;
    private int chatId;
    private String name;
    private String avatar;

    public ChatAppDTO(ChatDescriptionDTO descriptionDTO) {
        this.chatId = descriptionDTO.getChatId();
        this.name = descriptionDTO.getName();
        this.avatar = Base64.getEncoder().encodeToString(
                descriptionDTO.getAvatar().orElseGet(() -> {
                    try {
                        return Files.readAllBytes(
                                resourceLoader.getResource("classpath:User.png").getFile().toPath()
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        if (avatar.equals("")) {
            try {
                avatar = Base64.getEncoder().encodeToString(Files.readAllBytes(
                        resourceLoader.getResource("classpath:User.png").getFile().toPath()
                ));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
