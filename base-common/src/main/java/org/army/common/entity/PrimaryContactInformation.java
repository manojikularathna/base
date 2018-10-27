package org.army.common.entity;

import javax.persistence.Embeddable;

@Embeddable
public class PrimaryContactInformation {

    private Long contactInformationId;

    private Address address;

    private String phoneNumber;

    private String emailAddress;

    public Long getContactInformationId() {
        return contactInformationId;
    }

    public void setContactInformationId(Long contactInformationId) {
        this.contactInformationId = contactInformationId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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