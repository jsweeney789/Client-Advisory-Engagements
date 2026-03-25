package com.skillstorm.jsweeney_proj1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.jsweeney_proj1.models.*;

/**
 * As we try to do test-driven development I will write some basic tests for the creation of the Java Objects required by my CRUD app first
 * these are Client - Advisory Service - Engagement
 */
public class javaObjectTests {

    @Test
    @DisplayName("Client Object Creation Test")
    public void clientJavaObjTest() {
        Client client = new Client(1, "Jacob", "Sweeney", "jsweeney@skillstorm.com", "+1-123-456-7890", "Premium", "250k");
        assertNotNull(client);
        assertEquals(client.getId(), 1);
        assertEquals(client.getFirstName(), "Jacob");
        assertEquals(client.getLastName(), "Sweeney");
        assertEquals(client.getEmail(), "jsweeney@skillstorm.com");
        assertEquals(client.getPhone(), "+1-123-456-7890");
        assertEquals(client.getTier(), "Premium");
        assertEquals(client.getEstNetWorth(), "250k");
    }

    @Test
    @DisplayName("Advisory Service Object Creation Test")
    public void advServJavaObjTest() {
        AdvisoryService advServ = new AdvisoryService(2, "Business Advisory LLC", "Tax Planning", "Virtual", 1_000.00);
        assertNotNull(advServ);
        assertEquals(advServ.getId(), 2);
        assertEquals(advServ.getName(), "Business Advisory LLC");
        assertEquals(advServ.getType(), "Tax Planning");
        assertEquals(advServ.getDeliveryFormat(), "Virtual");
        assertEquals(advServ.getAnnualFee(), 1000.00);
    }
    
    @Test
    @DisplayName("Engagement Service Object Creation Test")
    public void engagementJavaObjTest() {
        LocalDate exampleDate = LocalDate.parse("2026-03-25");
        Engagement engagement = new Engagement(1, 1, 1, exampleDate, engagement.engagementStatus.ACTIVE);
        assertNotNull(engagement);
        assertEquals(engagement.getId(), 1);
        assertEquals(engagement.getClientId(), 1);
        assertEquals(engagement.getAdvisoryId(), 2);
        assertEquals(engagement.getStartDate(), exampleDate);
        assertEquals(engagement.getStatus(), engagement.engagementStatus.ACTIVE);
    }
}
