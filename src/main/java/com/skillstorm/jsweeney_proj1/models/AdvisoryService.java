package com.skillstorm.jsweeney_proj1.models;

import jakarta.persistence.Entity;

@Entity
public class AdvisoryService {
    // again, unsure for now if the enums make sense or not
    // using for now because these seem great for dropdown menus in frontend
    public enum serviceType{TAX, ESTATE, PORTFOLIO, RETIREMENT}
    public enum deliveryFormatOptions{INPERSON, VIRTUAL, HYBRID}

    private long advisoryServiceId;
    private String name;
    private serviceType serviceType;
    private deliveryFormatOptions deliveryFormat;
    private double annualFee;

    
    public AdvisoryService(long advisoryServiceId, String name, serviceType service,
            deliveryFormatOptions deliveryFormat, double annualFee) {
        this.advisoryServiceId = advisoryServiceId;
        this.name = name;
        this.serviceType = service;
        this.deliveryFormat = deliveryFormat;
        this.annualFee = annualFee;
    }


    public long getAdvisoryId() {
        return advisoryServiceId;
    }


    public void setAdvisoryServiceId(long advisoryServiceId) {
        this.advisoryServiceId = advisoryServiceId;
    }


    public String getName() {
        return name;
    }


    public void setName(String serviceName) {
        this.name = serviceName;
    }


    public String getType() {
        return serviceType.toString();
    }


    public void setType(serviceType serviceType) {
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
