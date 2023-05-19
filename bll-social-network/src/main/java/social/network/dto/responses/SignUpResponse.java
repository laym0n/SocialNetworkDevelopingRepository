package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignUpResponse {
    private boolean isSuccessful;
}
