package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import social.network.entities.socialnetworkuser.HumanUser;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAppDTO {
    public static ResourceLoader resourceLoader;
    private int userId;
    private String firstName;
    private String secondName;
    private String avatar;
    public UserAppDTO(HumanUser humanUser) {
        this.userId = humanUser.getUserInfo().getIdUser();
        this.avatar = Base64.getEncoder().encodeToString(
                humanUser.getAvatar().orElseGet(() -> {
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
        this.firstName = humanUser.getFirstName();
        this.secondName = humanUser.getSecondName();
    }
}
