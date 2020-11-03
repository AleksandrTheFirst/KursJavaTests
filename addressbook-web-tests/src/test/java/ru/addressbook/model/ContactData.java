package ru.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String address;
    private final String mobilePhone;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String companyName, String address, String mobilePhone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.companyName = companyName;
        this.address = address;
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }


}
