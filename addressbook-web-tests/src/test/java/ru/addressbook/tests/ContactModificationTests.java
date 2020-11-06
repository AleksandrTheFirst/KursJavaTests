package ru.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Igor")
                    .withMiddleName("Sergeevich")
                    .withLastName("Alekseev")
                    .withCompanyName("Zebra Ltd.")
                    .withNickName("ASA")
                    .withAddress("Moscow, Kremlin")
                    .withMobilePhone("+79995556644");
            app.contact().create(contact);
        }
    }

    @Test
    public void contactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstName("Sergey")
                .withLastName("Alekseev")
                .withId(modifiedContact.getId());
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
