package com.skillstorm.jsweeney_proj1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.jsweeney_proj1.Dtos.AdvisoryDto;
import com.skillstorm.jsweeney_proj1.models.Advisory;

@Repository
public interface AdvisoryRepository extends JpaRepository<Advisory, Long> {
    @Query(value = """
        select a.advisory_service_id,
            a.service_name,
            a.service_type,
            a.delivery_format,
            a.is_active_status,
            a.annual_fee,
            COUNT(e.client_id) 
        from advisory_service as a
        LEFT JOIN engagement as e ON a.advisory_service_id=e.advisory_service_id 
        GROUP BY a.advisory_service_id;
            """, nativeQuery = true // used left join because we need to keep advisories with 0 engagements
        ) List<AdvisoryDto> getAllAdvisoriesWithClientCounts(); 
}
