package com.web.hosp.repos;

import com.web.hosp.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointRepository extends JpaRepository<Appointment, Integer> {
}
