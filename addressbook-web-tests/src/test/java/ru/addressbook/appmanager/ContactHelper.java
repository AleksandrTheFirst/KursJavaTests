package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void returnToContactsPage() {
        click(By.linkText("home"));
    }

    public void initContactModification(int index) {
        click(By.xpath("//a[@href='edit.php?id=" + index + "'" + "]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContact(int id) {
        click(By.id(Integer.toString(id)));
    }

    public void deleteContact() {
        click(By.xpath("//*[@value = 'Delete']"));
    }

    public void acceptContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToContactsPage();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnToContactsPage();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteContact();
        contactCache = null;
        acceptContactDeletion();
        returnToContactsPage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = columns.get(5).getText();
            String address = columns.get(3).getText();
            String allEmails = columns.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData()
                    .withFirstName(name)
                    .withLastName(lastName)
                    .withId(id)
                    .withAllPhones(allPhones)
                    .withAddress(address)
                    .withAllEmails(allEmails));
        }

        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new ContactData().withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }
}
