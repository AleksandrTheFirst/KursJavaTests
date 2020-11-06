package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

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
    public void contactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(
                "Sergey",
                null,
                "Alekseev",
                null,
                null,
                null,
                null,
                before.get(before.size() - 1).getId()
        );
        app.contact().modify(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size());


        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
