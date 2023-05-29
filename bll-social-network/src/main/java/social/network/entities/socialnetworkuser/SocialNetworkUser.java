package social.network.entities.socialnetworkuser;

import java.util.Optional;

public abstract class SocialNetworkUser {
    private Optional<byte[]> avatar;
    public abstract String getName();
}
