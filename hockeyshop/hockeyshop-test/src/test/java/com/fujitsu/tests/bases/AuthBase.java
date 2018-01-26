package com.fujitsu.tests.bases;

import com.fujitsu.tests.models.User;
import com.fujitsu.tests.utils.CredentialsRetriever;
import org.junit.Before;

/**
 * Created by User on 21.01.2018.
 */
public class AuthBase extends TestBase {

    private final String userName = CredentialsRetriever.getUserName();
    private final String userPassword = CredentialsRetriever.getUserPassword();
    public User user1 = new User(userName,userPassword);

    @Before
    public void createUser() throws Exception {

        user1 = new User(userName,userPassword);
        appManager.getLoginHelper().login(user1);
    }

}
