package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileInfoRequest {
    private String firstName;
    private String secondName;
    private LocalDate birthday;
}
