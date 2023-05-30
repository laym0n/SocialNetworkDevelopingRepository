package social.network.entities.socialnetworkuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SocialNetworkUser {
    private Optional<byte[]> avatar;
    public abstract String getName();
}
