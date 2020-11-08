package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Igor")
                    .withMiddleName("Sergeevich")
                    .withLastName("Alekseev")
                    .withEmail("79995556644@mail.ru")
                    .withEmail2("79995556644@ya.ru")
                    .withEmail3("79995556644@list.com");
            app.contact().create(contact);
        }
    }


    @Test
    public void contactEmails() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactDataFromEditForm)));
    }


    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleanEmails)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanEmails(String email) {
        return email.replaceAll("[@.]", "");
    }
}
