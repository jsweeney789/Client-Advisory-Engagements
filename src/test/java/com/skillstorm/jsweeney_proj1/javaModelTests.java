package com.skillstorm.jsweeney_proj1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.skillstorm.jsweeney_proj1.models.*;
import com.skillstorm.jsweeney_proj1.models.Client.networthRange;
import com.skillstorm.jsweeney_proj1.models.AdvisoryService.deliveryFormatOptions;
import com.skillstorm.jsweeney_proj1.models.AdvisoryService.serviceType;
import com.skillstorm.jsweeney_proj1.models.Engagement.engagementStatus;



/**
 * As we try to do test-driven development I will write some basic tests for the creation of the Java Objects required by my CRUD app first
 * these are Client - Advisory Service - Engagement
 */
@SpringBootTest
public class javaModelTests {

    @Test
    @DisplayName("Client Object Creation Test")
    public void clientCreationTest() {
        Client client = new Client(1L, "Jacob", "Sweeney", "jsweeney@skillstorm.com", 
        "+1-123-456-7890", "Premium", 250_000.00);
        assertNotNull(client);
        assertEquals(client.getClientId(), 1);
        assertEquals(client.getFirstName(), "Jacob");
        assertEquals(client.getLastName(), "Sweeney");
        assertEquals(client.getEmail(), "jsweeney@skillstorm.com");
        assertEquals(client.getPhone(), "+1-123-456-7890");
        assertEquals(client.getTier(), "Premium");
        assertEquals(client.getEstNetWorth(), networthRange.UNDER_500K);
    }

    @Test
    @DisplayName("Advisory Service Object Creation Test")
    public void advServCreationTest() {
        AdvisoryService advServ = new AdvisoryService(2, "Business Advisory LLC", 
        serviceType.TAX, deliveryFormatOptions.VIRTUAL, 1_000.00);
        assertNotNull(advServ);
        assertEquals(advServ.getAdvisoryId(), 2);
        assertEquals(advServ.getName(), "Business Advisory LLC");
        assertEquals(advServ.getType(), "TAX");
        assertEquals(advServ.getDeliveryFormat(), "VIRTUAL");
        assertEquals(advServ.getAnnualFee(), 1000.00);
    }
    
    @Test
    @DisplayName("Engagement Service Object Creation Test")
    public void engagementCreationTest() {
        LocalDate exampleDate = LocalDate.parse("2026-03-25");
        Engagement engagement = new Engagement(1L, 1L, 2L, exampleDate);
        assertNotNull(engagement);
        assertEquals(engagement.getEngagementId(), 1);
        assertEquals(engagement.getClientId(), 1);
        assertEquals(engagement.getAdvisoryId(), 2);
        assertEquals(engagement.getStartDate(), exampleDate);
        assertEquals(engagement.getStatus(), "ACTIVE");
    }
}
