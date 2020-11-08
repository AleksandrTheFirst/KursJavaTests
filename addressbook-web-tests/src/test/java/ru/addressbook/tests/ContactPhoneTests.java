package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Igor")
                    .withMiddleName("Sergeevich")
                    .withLastName("Alekseev")
                    .withMobilePhone("+7(999)5556644")
                    .withHomePhone("123 123")
                    .withWorkPhone("23-42-34");
            app.contact().create(contact);
        }
    }

    @Test
    public void contactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleanPhones)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
