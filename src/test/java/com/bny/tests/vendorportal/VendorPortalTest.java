package com.bny.tests.vendorportal;

import com.bny.pages.vendorportal.model.VendorPortalTestData;
import com.bny.tests.BaseTest;
import com.bny.pages.vendorportal.DashboardPage;
import com.bny.pages.vendorportal.LoginPage;
import com.bny.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }
    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMarginElement(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(), testData.searchResultCount());

    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }


}
