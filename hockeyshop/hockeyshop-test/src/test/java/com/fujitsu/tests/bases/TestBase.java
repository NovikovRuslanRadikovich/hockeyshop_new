package com.fujitsu.tests.bases;

import com.fujitsu.tests.AppManager;
import org.junit.Before;

/**
 * Created by User on 21.01.2018.
 */
public class TestBase {

    protected StringBuffer verificationErrors = new StringBuffer();

    protected  AppManager appManager;

    protected TestBase() {

    }

    @Before
    public void setUp() throws Exception {
        appManager = AppManager.getInstance();
        appManager.getNavigationHelper().getHomePage();
    }

}
