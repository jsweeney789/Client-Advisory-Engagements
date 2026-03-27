package com.skillstorm.jsweeney_proj1.models;

import jakarta.persistence.Entity;

@Entity
public class Advisory {
    // again, unsure for now if the enums make sense or not
    // using for now because these seem great for dropdown menus in frontend
    public enum serviceType{TAX, ESTATE, PORTFOLIO, RETIREMENT}
    public enum deliveryFormatOptions{INPERSON, VIRTUAL, HYBRID}

    private long advisoryId;
    private String name;
    private serviceType serviceType;
    private deliveryFormatOptions deliveryFormat;
    private double annualFee;

    
    public Advisory(long advisoryId, String name, serviceType service,
            deliveryFormatOptions deliveryFormat, double annualFee) {
        this.advisoryId = advisoryId;
        this.name = name;
        this.serviceType = service;
        this.deliveryFormat = deliveryFormat;
        this.annualFee = annualFee;
    }


    public long getAdvisoryId() {
        return advisoryId;
    }


    public void setAdvisoryServiceId(long advisoryServiceId) {
        this.advisoryId = advisoryServiceId;
    }


    public String getName() {
        return name;
    }


    public void setName(String serviceName) {
        this.name = serviceName;
    }


    public String getServiceType() {
        return serviceType.toString();
    }


    public void setServiceType(serviceType serviceType) {
        this.serviceType = serviceType;
    }


    public String getDeliveryFormat() {
        return deliveryFormat.toString();
    }


    public void setDeliveryFormat(deliveryFormatOptions deliveryFormat) {
        this.deliveryFormat = deliveryFormat;
    }


    public double getAnnualFee() {
        return annualFee;
    }


    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }
    
}
