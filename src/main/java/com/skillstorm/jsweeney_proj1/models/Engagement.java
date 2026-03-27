package com.skillstorm.jsweeney_proj1.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;

/**
 * Engagements are relationships between a client and an advisoryService
 */
@Entity
public class Engagement {
    public enum engagementStatus{ACTIVE, PAUSED, COMPLETED}

    private long engagementId;
    private Client client; // the id of the client associated with the engagement
    private Advisory advisory; // the id of the advisory Service associated with the engagement
    private LocalDate startDate;
    private engagementStatus status;


    public Engagement(long engagementId, Client client, Advisory advisory, LocalDate start_date) {
        this.engagementId = engagementId;
        this.client = client;
        this.advisory = advisory;
        this.startDate = LocalDate.now(); // will use backend timer for Date, though DB also sets NOW() itself as default
        this.status = engagementStatus.ACTIVE; // assuming engagements are active on creation for now
    }

    public long getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(long engagementId) {
        this.engagementId = engagementId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Advisory getAdvisory() {
        return advisory;
    }

    public void setAdvisory(Advisory advisory) {
        this.advisory = advisory;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate start_date) {
        this.startDate = start_date;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(engagementStatus status) {
        this.status = status;
    }

    
}

