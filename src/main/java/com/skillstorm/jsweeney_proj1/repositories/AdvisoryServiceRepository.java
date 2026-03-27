package com.skillstorm.jsweeney_proj1.repositories;

import org.springframework.boot.json.JsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.jsweeney_proj1.models.AdvisoryService;

@Repository
public interface AdvisoryServiceRepository extends JpaRepository<AdvisoryService, Long> {

}
