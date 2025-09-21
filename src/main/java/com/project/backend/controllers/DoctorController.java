package com.project.backend.controllers;

import com.project.backend.payload.ApiResponse;
import com.project.backend.services.DoctorService;
import com.project.backend.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    public DoctorController(DoctorService doctorService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    // Example: GET /api/doctors/{id}/availability?date=2025-10-10&token=xxx
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<ApiResponse> getAvailability(
            @PathVariable Long doctorId,
            @RequestParam("date") String date,
            @RequestParam("token") String token
    ) {
        if (!tokenService.isTokenValid(token)) {
            return ResponseEntity.status(401).body(ApiResponse.error("Invalid or missing token"));
        }

        var slots = doctorService.getAvailableTimeSlots(doctorId, LocalDate.parse(date));
        return ResponseEntity.ok(ApiResponse.success("Available time slots", slots));
    }
}
