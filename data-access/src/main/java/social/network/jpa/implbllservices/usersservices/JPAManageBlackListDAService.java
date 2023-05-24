package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import social.network.jpa.dao.UsersBlackListDAO;
import social.network.daservices.ManageBlackListDAService;
import social.network.jpa.entities.BlackListUserEntity;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;

@Service
@AllArgsConstructor
public class JPAManageBlackListDAService implements ManageBlackListDAService {
    private UsersBlackListDAO usersBlackListDAO;

    @Override
    public void saveBlackListRelationshipAndCheckAlreadyExists(BlackListRelationship blackListRelationship) {
        boolean isAlreadyExists = usersBlackListDAO.existsById(
                new BlackListUserEntityId(blackListRelationship)
        );
        if (isAlreadyExists) {
            throw new EntityAlreadyExistsException("User with id " + blackListRelationship.getIdBlockedUser()
                    + " already in black list of user with id " + blackListRelationship.getIdOwnerBlackList());
        }
        BlackListUserEntity newEntity = new BlackListUserEntity(blackListRelationship);
        usersBlackListDAO.create(newEntity);
    }

    @Override
    public void deleteBlackListRelationship(BlackListRelationship blackListRelationship) {
        try {
            usersBlackListDAO.deleteById(
                    new BlackListUserEntityId(
                            blackListRelationship.getIdOwnerBlackList(),
                            blackListRelationship.getIdBlockedUser()
                    )
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("User with id " + blackListRelationship.getIdBlockedUser()
                + " was not in black list of user with id " + blackListRelationship.getIdOwnerBlackList());
        }
    }
}
