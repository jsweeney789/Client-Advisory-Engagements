package com.skillstorm.jsweeney_proj1.layerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.jsweeney_proj1.models.Client;

// The service layer interacts with the controller and the repository - handles "business logic"
// this class is meant to test them as a single component
public class serviceLayerTests {
    ClientService clientService;

    // no clue if this is necessary but it feels right?
    @BeforeAll
    public void initClientService() {
        this.clientService = new ClientService();
    }

    /**
     * Starting with client model
     * Want to test basic CRUD behavior, so our goal is to test and then require the making of
     * Create
     * Read
     * Update
     * Delete
     * TODO: might need to BeforeAll initialize some kind of repo layer for the service to interact with in testing, unsure
     */
    @Test
    @DisplayName("Test Client Service Create")
    public void serviceCreateClientTest() {
        Client exampleClient = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", "Standard", 750_000.00);
        Client resultClient = clientService.createClient(exampleClient);
        assertNotNull(resultClient);
        assertEquals(exampleClient.getClientId(), resultClient.getClientId());
        assertEquals(exampleClient.getFirstName(), resultClient.getFirstName());
        assertEquals(exampleClient.getLastName(), resultClient.getLastName());
        assertEquals(exampleClient.getEmail(), resultClient.getEmail());
        assertEquals(exampleClient.getPhone(), resultClient.getPhone());
        assertEquals(exampleClient.getTier(), resultClient.getTier());
        assertEquals(exampleClient.getEstNetWorth(), resultClient.getEstNetWorth());
    }

    @Test
    @DisplayName("Test Client Service Read")
    public void serviceGetClientTest() {
        Client exampleClient = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", "Standard", 750_000.00);
        clientService.createClient(exampleClient);
        Client resultClient = clientService.getClientById(1L);
        assertNotNull(resultClient);
        assertEquals(exampleClient.getClientId(), resultClient.getClientId());
        assertEquals(exampleClient.getFirstName(), resultClient.getFirstName());
        assertEquals(exampleClient.getLastName(), resultClient.getLastName());
        assertEquals(exampleClient.getEmail(), resultClient.getEmail());
        assertEquals(exampleClient.getPhone(), resultClient.getPhone());
        assertEquals(exampleClient.getTier(), resultClient.getTier());
        assertEquals(exampleClient.getEstNetWorth(), resultClient.getEstNetWorth());
    }

    @Test
    @DisplayName("Test Client Service Update")
    public void serviceUpdateClientTest() {
        Client exampleClient = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", "Standard", 750_000.00);
        clientService.createClient(exampleClient);
        exampleClient.setEmail("differentEmail@gmail.com");
        clientService.updateClient(exampleClient);
        Client resultClient = clientService.getClientById(1L);
        assertNotNull(resultClient);
        assertEquals(exampleClient.getEmail(), resultClient.getEmail());
    }

    @Test
    @DisplayName("Test Client Service Delete")
    public void serviceDeleteClientTest() {
        Client exampleClient = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", "Standard", 750_000.00);
        clientService.createClient(exampleClient);
        clientService.removeClient(1L); // will likely implement both a by id or by full object system, for now just testing id
        assertNull(clientService.getClientById(1L)); // not sure if null is the behavior we want, but for now it will be our goal
        // TODO: test truly desired behavior later on, like ResponseBody codes, exceptions, etc.
    }

    // TODO: write tests for the service layer of our advisory and engagement models
    // note that we might have to call it advisoryServiceService, that might be the worst thing ever
}
