package com.project.backend.services;

import com.project.backend.models.Appointment;
import com.project.backend.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;

    public AppointmentService(AppointmentRepository appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    /** Booking and saving an appointment */
    public Appointment book(Appointment appointment) {
        // Simple double-book check for same doctor & time
        boolean exists = appointmentRepo.existsByDoctor_DoctorIdAndAppointmentTime(
                appointment.getDoctor().getDoctorId(),
                appointment.getAppointmentTime()
        );
        if (exists) throw new IllegalStateException("Time slot already booked");
        return appointmentRepo.save(appointment);
    }

    /** Get all appointments for a doctor on a specific date */
    public List<Appointment> getForDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        return appointmentRepo.findByDoctor_DoctorIdAndAppointmentTimeBetween(doctorId, start, end);
    }
}
