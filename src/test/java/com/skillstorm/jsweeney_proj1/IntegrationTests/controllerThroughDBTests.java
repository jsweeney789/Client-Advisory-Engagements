package com.skillstorm.jsweeney_proj1.IntegrationTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.jsweeney_proj1.models.Advisory;
import com.skillstorm.jsweeney_proj1.models.Advisory.deliveryFormatOptions;
import com.skillstorm.jsweeney_proj1.models.Advisory.serviceType;
import com.skillstorm.jsweeney_proj1.models.Client;
import com.skillstorm.jsweeney_proj1.models.Client.tier;
import com.skillstorm.jsweeney_proj1.models.Engagement;
import com.skillstorm.jsweeney_proj1.models.Engagement.engagementStatus;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// won't lie I looked it up and Gemini told me about this test stuff for connecting to a test db
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class controllerThroughDBTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Test
    @DisplayName("Single Client CRUD Test")
    public void clientCRUDTest () throws Exception {
        Client example = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", tier.STANDARD, 750_000.00);
        
        // Create (save) test
        mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));

        // Read (get) test - only testing one at a time for now, will test getAll() along with other list stuff later
        mockMvc.perform(get("/clients/{id}", example.getClientId())).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));
        // test read fail
        mockMvc.perform(get("/clients/{id}", 10L))
            .andExpect(status().isNotFound());

        // Update (post) test 
        String oldEmail = example.getEmail();
        example.setEmail("jsmith@skillstorm.com");
        MvcResult result = mockMvc.perform(put("/clients/{id}", example.getClientId()).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)))
            .andReturn();
        
        // I only learned about mvc result and .andReturn() while doing this. I think the tests are fine without it but I want to try it out.
        //https://www.baeldung.com/spring-mockmvc-fetch-json
        String json = result.getResponse().getContentAsString();
        Client resultClient = objectMapper.readValue(json, new TypeReference<>(){});
        assertNotEquals(resultClient.getEmail(), oldEmail);


        // Delete test (and teardown)
        mockMvc.perform(delete("/clients/{id}", example.getClientId()))
            .andExpect(status().isNoContent());
        
        // test delete fail
        mockMvc.perform(delete("/clients/{id}", 10L))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Single Advisory CRUD Test")
    public void advisoryCRUDTest () throws Exception {
        Advisory example = new Advisory(1L, "Business Advisory Services LLC ", serviceType.TAX, deliveryFormatOptions.HYBRID, 1_000.00);
        
        // Create (save) test
        mockMvc.perform(post("/advisories").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));

        // Read (get) test - only testing one at a time for now, will test getAll() along with other list stuff later
        mockMvc.perform(get("/advisories/{id}", example.getAdvisoryId())).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));
        // test read fail
        mockMvc.perform(get("/advisories/{id}", 10L))
            .andExpect(status().isNotFound());

        // Update (post) test 
        String oldName = example.getName();
        example.setName("Small Business Advisory Services LLC");
        MvcResult result = mockMvc.perform(put("/advisories/{id}", example.getAdvisoryId()).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)))
            .andReturn();
        
        String json = result.getResponse().getContentAsString();
        Client resultClient = objectMapper.readValue(json, new TypeReference<>(){});
        assertNotEquals(resultClient.getEmail(), oldName);


        // Delete test (and teardown)
        mockMvc.perform(delete("/advisories/{id}", example.getAdvisoryId()))
            .andExpect(status().isNoContent());
        
        // test delete fail
        mockMvc.perform(delete("/advisories/{id}", 10L))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Single Engagement CRUD Test")
    public void engagementCRUDTest () throws Exception {
        Client exampleClient = new Client(1L, "John ", "Smith", "jsmith@gmail.com",
                                                 "1234567890", tier.STANDARD, 750_000.00);
        Advisory exampleAdvisory = new Advisory(1L, "Business Advisory Services LLC ", serviceType.TAX, deliveryFormatOptions.HYBRID, 1_000.00);
        LocalDate exampleDate = LocalDate.of(2026, 3, 27);
        Engagement example = new Engagement(1L, exampleClient, exampleAdvisory, exampleDate, "example notes!.", engagementStatus.PAUSED);
        
        // adding the client and advisory to the db as well as the engagement depends on them
        mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exampleClient)));
        mockMvc.perform(post("/advisories").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exampleAdvisory)));

        // Create (save) test
        mockMvc.perform(post("/engagements").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));

        // Read (get) test - only testing one at a time for now, will test getAll() along with other list stuff later
        mockMvc.perform(get("/engagements/{id}", example.getEngagementId())).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)));
        // test read fail
        mockMvc.perform(get("/engagements/{id}", 10L))
            .andExpect(status().isNotFound());

        // Update (post) test 
        String oldNotes = example.getNotes();
        example.setNotes("These are new notes!");
        MvcResult result = mockMvc.perform(put("/engagements/{id}", example.getEngagementId()).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(example)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(example)))
            .andReturn();
        
        String json = result.getResponse().getContentAsString();
        Client resultClient = objectMapper.readValue(json, new TypeReference<>(){});
        assertNotEquals(resultClient.getEmail(), oldNotes);


        // Delete test (and teardown)
        mockMvc.perform(delete("/engagements/{id}", example.getEngagementId()))
            .andExpect(status().isNoContent());
        
        // test delete fail
        mockMvc.perform(delete("/engagements/{id}", 10L))
            .andExpect(status().isNotFound());

        // tearing down the client and advisory we made too
        mockMvc.perform(delete("/clients/{id}", exampleClient.getClientId())).andExpect(status().isNoContent());
        mockMvc.perform(delete("/advisories/{id}", exampleAdvisory.getAdvisoryId())).andExpect(status().isNoContent());
    }
}
