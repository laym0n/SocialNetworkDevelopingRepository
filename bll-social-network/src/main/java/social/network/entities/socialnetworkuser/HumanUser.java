package social.network.entities.socialnetworkuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserRole;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanUser extends SocialNetworkUser {
    private UserInfo userInfo;
    private String firstName;
    private String secondName;
    private Map<Integer, UserRole> roles;

    public HumanUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String getName() {
        return firstName + " " + secondName;
    }
}
