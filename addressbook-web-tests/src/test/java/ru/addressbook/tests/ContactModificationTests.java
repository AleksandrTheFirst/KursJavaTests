package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModification() {
        app.getNavigationHelper().goToContactsPage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData(
                "Sergey",
                "Sergeevich",
                "Alekseev",
                "ASA",
                "Zebra Ltd.",
                "Moscow, Kremlin",
                "+79995556644"));
        app.getContactHelper().submitContactModification();
    }
}
