package com.bny.tests.vendorportal;

import com.bny.tests.BaseTest;
import com.bny.pages.vendorportal.DashboardPage;
import com.bny.pages.vendorportal.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeTest
    public void setPageObjects(){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }
    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam","sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),"$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), "$215,000");
        Assert.assertEquals(dashboardPage.getProfitMarginElement(),"50%");
        Assert.assertEquals(dashboardPage.getAvailableInventory(), "18");
        // order history search
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultCount(), 8);

    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }


}
