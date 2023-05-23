package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.UserInfo;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserResponse {
    private UserInfoDTO[] users;
    public FindUserResponse(Collection<UserInfo> userInfos) {
        users = userInfos.stream().map(i-> new UserInfoDTO(i)).toArray(UserInfoDTO[]::new);
    }
}
