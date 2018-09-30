package org.army.base.common.to;

public class PrimaryContactInformationTO {

    private Long contactInformationId;

    private AddressTO address;

    private String phoneNumber;

    private String emailAddress;

    public Long getContactInformationId() {
        return contactInformationId;
    }

    public void setContactInformationId(Long contactInformationId) {
        this.contactInformationId = contactInformationId;
    }

    public AddressTO getAddress() {
        return address;
    }

    public void setAddress(AddressTO address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}