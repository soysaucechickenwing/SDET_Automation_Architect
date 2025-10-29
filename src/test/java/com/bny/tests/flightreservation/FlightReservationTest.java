package com.bny.tests.flightreservation;

import com.bny.pages.vendorportal.model.FlightReservationTestData;
import com.bny.tests.BaseTest;
import com.bny.pages.flightreservation.*;
import com.bny.tests.vendorportal.VendorPortalTest;
import com.bny.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {

    private FlightReservationTestData flightReservationTestData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String flightReservationTestData){

        this.flightReservationTestData = JsonUtil.getTestData(flightReservationTestData, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetail(flightReservationTestData.firstName() ,flightReservationTestData.lastName());
        registrationPage.enterUserCredential(flightReservationTestData.email(),flightReservationTestData.password());
        registrationPage.enterAddress(flightReservationTestData.street(),flightReservationTestData.city(),flightReservationTestData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), flightReservationTestData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }


    @Test(dependsOnMethods  = "registrationConfirmationTest")
    public void flightSearchTest() throws InterruptedException {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(flightReservationTestData.passengersCount());
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

        Assert.assertEquals(flightConfirmationPage.getPrice(),flightReservationTestData.expectedPrice());

    }


}
