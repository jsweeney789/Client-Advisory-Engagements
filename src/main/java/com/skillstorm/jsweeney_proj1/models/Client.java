package com.skillstorm.jsweeney_proj1.models;

import jakarta.persistence.Entity;

@Entity
public class Client {
    // I made these enums exist but I'm not sure if I want to use them yet 
    // TODO: Ask Austin at code review if people use enums in development for cases like these
    public enum tier{STANDARD, PREMIUM, PRIVATE_BANKING}
    public enum networthRange{UNDER_500K, BETWEEN_500K_2M, BETWEEN_2M_10M, OVER_10M}

    private long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String tier;
    private networthRange clientNetWorth;

    /**
     * Helper method that allows our constructor to receive a net worth estimation and then map it to
     * the ranges we care about
     * @param netWorth the estimated net worth of the client
     * @return the range that the client is in which we usually filter on
     */
    private networthRange mapNetWorth(double netWorth) {
        if (netWorth < 500_000.00) {
            return networthRange.UNDER_500K;

        } else if (netWorth < 2_000_000) {
            return networthRange.BETWEEN_500K_2M;

        } else if (netWorth < 10_000_000) {
            return networthRange.BETWEEN_2M_10M;

        } else {
            return networthRange.OVER_10M;
        }
    }

    /**
     * Constructor method for the client object
     * @param firstName first name of client
     * @param lastName last name of client
     * @param email client's email address
     * @param phone client's contact phone number
     * @param clientTier the client's tier of service with us
     * @param netWorth the estimated net worth of the client
     */
    public Client(long id, String firstName, String lastName, String email, String phone, String clientTier,
            double netWorth) {
        this.clientId = id; // generate id somewhere somehow and store it
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.tier = clientTier;
        this.clientNetWorth = mapNetWorth(netWorth);
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String clientTier) {
        this.tier = clientTier;
    }

    public networthRange getEstNetWorth() {
        return clientNetWorth;
    }

    public void setClientNetWorth(networthRange clientNetWorth) {
        this.clientNetWorth = clientNetWorth;
    }

    
}
