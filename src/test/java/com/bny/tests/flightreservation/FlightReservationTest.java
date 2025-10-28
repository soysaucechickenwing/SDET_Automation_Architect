package com.bny.tests.flightreservation;

import com.bny.tests.BaseTest;
import com.bny.pages.flightreservation.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setPageObjects(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;

    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetail("Tony","Leung");
        registrationPage.enterUserCredential("tony.leung@gmail.com","Password123!");
        registrationPage.enterAddress("123 main st","Boston","02125");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goToFlightSearch();
    }


    @Test(dependsOnMethods  = "registrationConfirmationTest")
    public void flightSearchTest() throws InterruptedException {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods =  "flightSearchTest")
    public void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlight();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);

    }


}
