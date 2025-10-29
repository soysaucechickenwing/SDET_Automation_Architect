package com.bny.pages.vendorportal;

import com.bny.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventory;

    @FindBy(css = "#dataTable_filter input") //equal to document.querySelector("#dataTable_filter") in developer console
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePicElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningElement.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningElement.getText();
    }

    public String getProfitMarginElement(){
        return this.profitMarginElement.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventory.getText();
    }

    public void searchOrderHistoryBy(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount(){
        String resultText = this.searchResultCountElement.getText();
        String[] arr = resultText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}",count);
        return count;
    }

    public void logout(){
        this.userProfilePicElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
    }
