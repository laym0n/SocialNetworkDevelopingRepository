package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRelationshipDTO {
    private boolean isFriend;
    private boolean isFriendRequestSentFromUserOwnerProfile;
    private boolean isFriendRequestSentForUserOwnerProfile;
    private boolean isCurrentUserInBlackList;
    private boolean isOwnerProfileInBlackList;
    private boolean isDialogChatExist;
}
