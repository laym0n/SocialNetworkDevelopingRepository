package social.network.usecases.usersusecases.impl.manageblacklist;

import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

public interface HandlerAddingToBlackList {
    void handle(BlackListRelationship blackListRelationship);
}
