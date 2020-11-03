package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static com.sun.xml.internal.ws.util.VersionUtil.compare;

public class ContactCreationTests extends TestBase {


    @Test
    public void createContact() {
        app.getNavigationHelper().goToContactsPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData(
                "Alex1",
                null,
                "Alekseev1",
                null,
                null,
                null,
                null);
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactsPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byNameAndLastName = (g1, g2) -> compare(g1.getFirstName() + " " + g1.getLastName(), g2.getFirstName() + " " + g2.getLastName());
        before.sort(byNameAndLastName);
        after.sort(byNameAndLastName);
        Assert.assertEquals(before, after);
    }
}
