package dataaccess.usecasestests.chatsusecases;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.implbllservices.chatsservices.ManagePrivateChatsDAServiceImpl;

public class ManagePrivateChatDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private ManagePrivateChatsDAServiceImpl SUT;

    @Test
    @Transactional
    @Rollback
    public void test() {

    }
}
