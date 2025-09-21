package com.project.backend.services;

import com.project.backend.models.Doctor;
import com.project.backend.payload.ApiResponse;
import com.project.backend.repositories.AppointmentRepository;
import com.project.backend.repositories.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;

    public DoctorService(DoctorRepository doctorRepo, AppointmentRepository appointmentRepo) {
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
    }

    /** Return available time slots for a doctor on the given date (simple 09:00–17:00 every hour) */
    public List<String> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        var taken = appointmentRepo.findByDoctor_DoctorIdAndAppointmentTimeBetween(
                doctorId, date.atStartOfDay(), LocalDateTime.of(date, LocalTime.MAX));

        Set<LocalTime> busy = new HashSet<>();
        taken.forEach(a -> busy.add(a.getAppointmentTime().toLocalTime().withMinute(0).withSecond(0).withNano(0)));

        List<String> slots = new ArrayList<>();
        for (int h = 9; h <= 17; h++) {
            LocalTime t = LocalTime.of(h, 0);
            if (!busy.contains(t)) slots.add(t.toString());
        }
        return slots;
    }

    /** Very simple login check by email only (demo). In thực tế sẽ có password hash. */
    public ResponseEntity<ApiResponse> validateLogin(String email) {
        Optional<Doctor> doctor = doctorRepo.findByEmail(email);
        if (doctor.isEmpty()) {
            return ResponseEntity.status(401).body(ApiResponse.error("Invalid credentials"));
        }
        return ResponseEntity.ok(ApiResponse.success("Login OK", Map.of("doctorId", doctor.get().getDoctorId())));
    }
}
