// src/main/java/com/example/mortgagecalculator/repository/ProspectRepository.java

package com.example.mortgagecalculator.repository;

import com.example.mortgagecalculator.model.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    // Custom queries or additional methods can be defined here
}