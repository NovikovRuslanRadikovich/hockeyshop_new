package com.fujitsu.tests.testers;

import com.fujitsu.tests.AppManager;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HomeTest.class,LoginTest.class})
public class AllTests {

    @AfterClass
    public static void quit() {
        AppManager.getInstance().getDriver().quit();
    }


}
