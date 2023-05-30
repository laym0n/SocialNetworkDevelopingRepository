package social.network.dto.requests;

import lombok.Builder;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String userName;
    private String password;
    private UserProfile userProfile;
    public User getUser() {
        return new User(userName, password);
    }

}
