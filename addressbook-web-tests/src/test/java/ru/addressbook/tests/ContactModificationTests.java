package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModification() {
        app.goTo().goToContactsPage();
            if (!app.getContactHelper().isThereAContact()) {
                ContactData contact = new ContactData(
                        "Igor",
                        "Sergeevich",
                        "Alekseev",
                        "ASA",
                        "Zebra Ltd.",
                        "Moscow, Kremlin",
                        "+79995556644");
                app.getContactHelper().createContact(contact);
            }
        List<ContactData> before = app.getContactHelper().getContactList();
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
        app.getContactHelper().modifyContact(contact);
        app.getContactHelper().returnToContactsPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());


        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
