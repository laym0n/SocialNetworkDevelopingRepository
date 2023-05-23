package social.network.daservices;

import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

public interface ManageBlackListDAService {
    void saveBlackListRelationshipAndCheckAlreadyExists(BlackListRelationship blackListRelationship);

    void deleteBlackListRelationship(BlackListRelationship blackListRelationship);
}
