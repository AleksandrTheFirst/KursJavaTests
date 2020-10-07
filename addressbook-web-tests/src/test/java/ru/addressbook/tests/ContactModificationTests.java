package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModification() {
        app.getNavigationHelper().goToContactsPage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "Igor",
                    "Sergeevich",
                    "Alekseev",
                    "ASA",
                    "Zebra Ltd.",
                    "Moscow, Kremlin",
                    "+79995556644"));
        }
        app.getContactHelper().modifyContact(new ContactData(
                "Sergey",
                null,
                null,
                null,
                null,
                null,
                null));
    }

}
