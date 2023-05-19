package social.network.usecases.getupdatesusecases.impl;

import social.network.daservices.GetUpdateDAService;
import social.network.dto.requests.UpdateRequest;
import social.network.dto.responses.GetUpdateResponse;
import lombok.AllArgsConstructor;
import social.network.usecases.getupdatesusecases.GetUpdateUseCase;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.time.OffsetDateTime;

@AllArgsConstructor
public class GetUpdateUseCaseImpl implements GetUpdateUseCase {
    private HandlerGetUpdate handlerGetUpdate;
    private GetUpdateDAService daService;
    private Duration maxDurationBetweenChecks;

    @Override
    public GetUpdateResponse getUpdateForUserById(UpdateRequest request) {
        OffsetDateTime lastCheckUpdateForUser = daService.findLastUpdateTimeForUser(request.getIdOwnerRequest());
        OffsetDateTime now = OffsetDateTime.now();
        Duration duration = Duration.between(lastCheckUpdateForUser, now);
        if (duration.compareTo(maxDurationBetweenChecks) >= 0) {
            throw new InvalidParameterException("Duration between last check update and current time is to greater");
        }
        GetUpdateResponse response = new GetUpdateResponse();
        handlerGetUpdate.getUpdateForUserById(request, response);
        return response;
    }
}
