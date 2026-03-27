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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (advisoryId ^ (advisoryId >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
        result = prime * result + ((deliveryFormat == null) ? 0 : deliveryFormat.hashCode());
        long temp;
        temp = Double.doubleToLongBits(annualFee);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Advisory other = (Advisory) obj;
        if (advisoryId != other.advisoryId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (serviceType != other.serviceType)
            return false;
        if (deliveryFormat != other.deliveryFormat)
            return false;
        if (Double.doubleToLongBits(annualFee) != Double.doubleToLongBits(other.annualFee))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Advisory [advisoryId=" + advisoryId + ", name=" + name + ", serviceType=" + serviceType
                + ", deliveryFormat=" + deliveryFormat + ", annualFee=" + annualFee + "]";
    }
    
}
