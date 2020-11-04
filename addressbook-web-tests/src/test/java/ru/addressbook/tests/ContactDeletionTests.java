package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactDeletion() {
        app.goTo().goToContactsPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "Igor",
                    "Sergeevich",
                    "Alekseev",
                    "ASA",
                    "Zebra Ltd.",
                    "Moscow, Kremlin",
                    "+79995556644"));
            before = app.getContactHelper().getContactList();
        }

        app.getContactHelper().deleteSelectedContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
