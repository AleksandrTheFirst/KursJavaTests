package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

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
    public void contactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
