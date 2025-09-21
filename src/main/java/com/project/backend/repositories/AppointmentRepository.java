package com.project.backend.repositories;

import com.project.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor_DoctorIdAndAppointmentTimeBetween(
            Long doctorId, LocalDateTime start, LocalDateTime end);

    boolean existsByDoctor_DoctorIdAndAppointmentTime(Long doctorId, LocalDateTime time);
}
