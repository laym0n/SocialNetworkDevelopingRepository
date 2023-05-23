package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUsersByFirstAndSecondNamesRequest {
    private int idUserSenderRequest;
    private String searchString;
}
