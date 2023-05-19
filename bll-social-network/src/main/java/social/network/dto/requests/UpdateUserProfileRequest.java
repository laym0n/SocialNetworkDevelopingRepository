package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequest {
    private int idOwnerRequest;
    private OffsetDateTime newBirthday;
    private UserInfoDTO userInfoDTO;

    public UserProfile getUserProfile() {
        UserInfo userInfo = userInfoDTO.getUserInfo();
        userInfo.setIdUser(idOwnerRequest);
        return new UserProfile(
                userInfo,
                newBirthday,
                null
        );
    }
}
