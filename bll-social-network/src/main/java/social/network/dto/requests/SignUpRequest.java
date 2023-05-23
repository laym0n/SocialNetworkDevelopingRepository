package social.network.dto.requests;

import lombok.Builder;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.User;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserRole;
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
    private UserInfoDTO userInfoDTO = new UserInfoDTO();

    public User getUser() {
        Map<Integer, UserRole> roles = new HashMap<>();
        roles.put(UserRole.SIMPLE_USER.getIdGroup(), UserRole.SIMPLE_USER);
        return new User(userName, password, roles);
    }
    public UserInfo getUserInfo(){
        return new UserInfo(0, userName, userInfoDTO.getFirstName(), userInfoDTO.getSecondName(),
                Optional.ofNullable(userInfoDTO.getAvatar()), null);
    }

}
