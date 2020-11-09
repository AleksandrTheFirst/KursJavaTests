package ru.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withFirstName("test1").withLastName("test1") });
        list.add(new Object[] {new ContactData().withFirstName("test2").withLastName("test2") });
        return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void createContact(ContactData contact) {
        app.goTo().contactsPage();
        Contacts before = app.contact().all();
//        File photo = new File("src/test/resources/123.png");
//        ContactData contact = new ContactData()
//                .withFirstName(name)
//                .withLastName(lastName)
//                .withPhoto(photo);

        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
