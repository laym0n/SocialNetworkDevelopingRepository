package social.network.jpa.implbllservices.getInfo;

import jakarta.persistence.SecondaryTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckFriendsDAService;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.usecases.getinfosusecases.CheckFriendsUseCase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckFriendsDAServiceImpl implements CheckFriendsDAService {
    private FriendDAO friendDAO;
    private UserDAO userDAO;
    @Override
    public List<HumanUser> findFriendsInfoForUser(int idUser) {
        List<FriendEntityId> idsAllFriends = friendDAO.findAllWithUser(idUser);
        Set<Integer> idsUsers = idsAllFriends.stream()
                .map(i-> (i.getFirstUserId() == idUser ? i.getSecondUserId() : i.getFirstUserId()))
                .collect(Collectors.toSet());
        List<UserEntity> users = userDAO.findAllByIds(idsUsers);
        return users.stream().map(UserEntity::getHumanUser).toList();
    }
}
