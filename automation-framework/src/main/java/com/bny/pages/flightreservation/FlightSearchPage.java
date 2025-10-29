package com.bny.pages.flightreservation;

import com.bny.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {
    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightButton;

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(this.passengerSelect); //This class is specific for dropdown select
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
        this.searchFlightButton.click();
    }
}
