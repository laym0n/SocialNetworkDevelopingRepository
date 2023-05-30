package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnbanUserRequest {
    private int idUserOwnerRequest;
    private int idUserForUnban;
}
