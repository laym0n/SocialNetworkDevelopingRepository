package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanUserRequest {
    private int idUserOwnerRequest;
    private int idUserForBan;
}
