package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.user.UserInfo;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String firstName;
    private String secondName;
    private byte[] avatar;
    public UserInfoDTO(UserInfo userInfo){
//        this.avatar = userInfo.getAvatar();
        this.firstName = userInfo.getFirstName();
        this.secondName = userInfo.getSecondName();
    }
    public UserInfo getUserInfo(){
        return new UserInfo(0, "", firstName, secondName, Optional.ofNullable(avatar), null);
    }

}
