package ru.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.appmanager.ContactHelper;
import ru.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests extends TestBase {


    @Test
    public void createContact() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData(
                "Alex",
                "Sergeevich",
                "Alekseev",
                "ASA",
                "Zebra Ltd.",
                "Moscow, Kremlin",
                "+79995556644"));
        app.getContactHelper().submitContactCreation();
    }
}
