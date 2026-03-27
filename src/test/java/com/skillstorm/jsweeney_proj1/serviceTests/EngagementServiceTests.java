package com.skillstorm.jsweeney_proj1.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skillstorm.jsweeney_proj1.models.Advisory;
import com.skillstorm.jsweeney_proj1.models.Advisory.deliveryFormatOptions;
import com.skillstorm.jsweeney_proj1.models.Advisory.serviceType;
import com.skillstorm.jsweeney_proj1.repositories.AdvisoryRepository;
import com.skillstorm.jsweeney_proj1.repositories.EngagementRepository;
import com.skillstorm.jsweeney_proj1.services.AdvisoryService;
import com.skillstorm.jsweeney_proj1.services.EngagementService;


// The service layer interacts with the controller and the repository - handles "business logic"
// this class is meant to test them as a single component
@ExtendWith(MockitoExtension.class)
public class EngagementServiceTests {
    @Mock
    private EngagementRepository repo;

    @InjectMocks
    EngagementService engagementService;


    // TODO: this -- also do annotations for the columns so that the repos can map them to the DB properly for our models
    // and also on every class? I think every controller and service and repo also need annotations
    
}
