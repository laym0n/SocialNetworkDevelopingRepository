package social.network.daservices;

import java.time.OffsetDateTime;

public interface GetUpdateDAService {
    OffsetDateTime findLastUpdateTimeForUser(int idUser);
}
