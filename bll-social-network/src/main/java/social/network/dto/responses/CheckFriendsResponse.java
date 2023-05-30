package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.socialnetworkuser.HumanUser;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckFriendsResponse {
    private List<HumanUser> friends;
}
