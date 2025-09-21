package com.project.backend.controllers;

import com.project.backend.models.Prescription;
import com.project.backend.payload.ApiResponse;
import com.project.backend.repositories.PrescriptionRepository;
import com.project.backend.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepo;
    private final TokenService tokenService;

    public PrescriptionController(PrescriptionRepository prescriptionRepo, TokenService tokenService) {
        this.prescriptionRepo = prescriptionRepo;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestParam("token") String token,
                                              @Valid @RequestBody Prescription body) {
        if (!tokenService.isTokenValid(token)) {
            return ResponseEntity.status(401).body(ApiResponse.error("Invalid token"));
        }
        var saved = prescriptionRepo.save(body);
        return ResponseEntity.ok(ApiResponse.success("Prescription saved", saved));
    }
}
