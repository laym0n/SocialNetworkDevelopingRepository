package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import social.network.dao.UsersBlackListDAO;
import social.network.daservices.ManageBlackListDAService;
import social.network.entities.BlackListUserEntity;
import social.network.entities.ids.BlackListUserEntityId;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class ManageBlackListDAServiceImpl implements ManageBlackListDAService {
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
        usersBlackListDAO.save(newEntity);
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
