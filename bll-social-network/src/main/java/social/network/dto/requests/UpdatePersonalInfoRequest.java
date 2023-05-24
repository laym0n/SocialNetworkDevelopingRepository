package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import social.network.entities.user.PersonalInfo;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonalInfoRequest {
    private int idOwnerRequest;
    private PersonalInfo updatedPersonalInfo;
}
