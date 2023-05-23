package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserProfileRequest {
    private int idOwnerRequest;
    private int idUserTarget;
    private int countFriends;
}
