package ru.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void GroupDeletionTest() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }
}
