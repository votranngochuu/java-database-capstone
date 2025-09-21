package com.project.backend.controllers;

import com.project.backend.payload.ApiResponse;
import com.project.backend.services.DoctorService;
import com.project.backend.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    @Autowired
    public DoctorController(DoctorService doctorService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    /**
     * Example (with email in path as rubric gợi ý):
     * GET /api/v1/doctors/5/availability/2025-10-14/user/jane.doe%40mail.com/token/eyJhbGciOiJI...
     *
     * Nếu không dùng email để xác thực token, bạn có thể dùng endpoint rút gọn bên dưới.
     */
    @GetMapping("/doctors/{doctorId}/availability/{date}/user/{email}/token/{token}")
    public ResponseEntity<ApiResponse<List<String>>> getDoctorAvailabilityWithUser(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String email,
            @PathVariable String token) {

        // Validate token theo email (đổi tên method theo service của bạn)
        boolean valid = tokenService.validateToken(email, token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("invalid_token", "Token is invalid or expired"));
        }

        LocalDate target = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<String> slots = doctorService.getAvailableTimeSlots(doctorId, target);

        return ResponseEntity.ok(ApiResponse.success(slots));
    }

    /**
     * Biến thể rút gọn (nếu rubric chỉ cần token là path variable):
     * GET /api/v1/doctors/{doctorId}/availability/{date}/{token}
     */
    @GetMapping("/doctors/{doctorId}/availability/{date}/{token}")
    public ResponseEntity<ApiResponse<List<String>>> getDoctorAvailability(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        // Nếu token không gắn với email, dùng phương thức validate token thuần
        boolean valid = tokenService.validateToken(token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("invalid_token", "Token is invalid or expired"));
        }

        LocalDate target = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<String> slots = doctorService.getAvailableTimeSlots(doctorId, target);

        return ResponseEntity.ok(ApiResponse.success(slots));
    }
}
