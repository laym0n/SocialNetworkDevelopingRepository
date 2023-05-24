package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAvatarRequest {
    private int idOwnerRequest;
    private Optional<byte[]> newAvatar;
}
