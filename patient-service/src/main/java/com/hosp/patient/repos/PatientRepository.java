package com.hosp.patient.repos;

import com.hosp.patient.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    int countByCreatedDateBetween(Date start, Date end);
    Page<Patient> findAll(Pageable pageable);
}
