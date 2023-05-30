package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.dto.DialogChatDTO;
import social.network.dto.FriendDTO;
import social.network.dto.UserRelationshipDTO;
import social.network.entities.user.UserProfile;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserProfileResponse {
    private int idUser;
    private UserProfile userProfile;
    private UserRelationshipDTO userRelationshipDTO;
    private DialogChatDTO dialogChatDTO;
}
