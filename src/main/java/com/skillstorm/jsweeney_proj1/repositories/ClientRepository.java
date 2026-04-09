package com.skillstorm.jsweeney_proj1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.jsweeney_proj1.Dtos.ClientDto;
import com.skillstorm.jsweeney_proj1.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // I wrote and tested this in my DB instead of learning JPA :/
    @Query(value = """
            select c.client_id AS clientId,
                c.first_name,
                c.last_name,
                c.email,
                c.phone,
                c.tier,
                c.net_worth,
                SUM(a.annual_fee) 
            from client as c
            JOIN engagement as e ON c.client_id=e.client_id
            JOIN advisory_service as a ON e.advisory_service_id=a.advisory_service_id
            GROUP BY c.client_id
            """,
            nativeQuery = true

    ) List<ClientDto> getAllClientsWithObligations();

}
