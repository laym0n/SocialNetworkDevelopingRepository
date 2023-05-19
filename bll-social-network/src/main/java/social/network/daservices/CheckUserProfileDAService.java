package social.network.daservices;

import social.network.entities.user.UserProfile;

public interface CheckUserProfileDAService {
    UserProfile loadUserProfileById(int idUserTarget, int idOwnerRequest);
}
