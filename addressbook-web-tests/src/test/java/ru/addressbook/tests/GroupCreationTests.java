package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

    @Test
    public void createGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
}
