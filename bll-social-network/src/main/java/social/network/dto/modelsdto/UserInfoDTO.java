package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.user.UserInfo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String firstName;
    private String secondName;
    private byte[] avatar;
    public UserInfo getUserInfo(){
        return new UserInfo(0, "", firstName, secondName, avatar);
    }

}
