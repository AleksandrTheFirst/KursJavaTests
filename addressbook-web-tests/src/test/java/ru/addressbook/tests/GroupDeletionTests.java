package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void GroupDeletionTest() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getGroupHelper().deleteSelectedGroup();
    }

}
