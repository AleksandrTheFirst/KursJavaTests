package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends  TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Igor")
                    .withMiddleName("Sergeevich")
                    .withLastName("Alekseev")
                    .withAddress("TestCity, testStreet, testHome 11-44.");
            app.contact().create(contact);
        }
    }

    @Test
    public void contactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactDataFromEditForm.getAddress()));
    }
}
