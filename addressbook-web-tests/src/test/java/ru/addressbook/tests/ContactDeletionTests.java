package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().list().size() == 0) {
            ContactData contact = new ContactData(
                    "Igor",
                    "Sergeevich",
                    "Alekseev",
                    "ASA",
                    "Zebra Ltd.",
                    "Moscow, Kremlin",
                    "+79995556644");
            app.contact().create(contact);
        }
    }

    @Test
    public void contactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
