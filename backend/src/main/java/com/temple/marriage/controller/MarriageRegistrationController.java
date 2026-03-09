package com.temple.marriage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temple.marriage.model.MarriageRegistration;
import com.temple.marriage.model.MarriageDocument;
import com.temple.marriage.service.MarriageRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MarriageRegistrationController {

    @Autowired
    private MarriageRegistrationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerMarriage(@RequestBody Map<String, Object> payload) {
        try {
            // 1. Extract Details
            Map<String, Object> regMap = (Map<String, Object>) payload.get("registration");
            MarriageRegistration registration = objectMapper.convertValue(regMap, MarriageRegistration.class);

            // 2. Extract Documents
            MarriageDocument documents = null;
            Map<String, Object> docsMap = (Map<String, Object>) payload.get("documents");
            if (docsMap != null) {
                documents = objectMapper.convertValue(docsMap, MarriageDocument.class);
            }

            // 3. Save Transactionally
            MarriageRegistration savedRegistration = service.registerWithDocuments(registration, documents);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "id", savedRegistration.getId(),
                    "message", "Registration and documents saved successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error saving details: " + e.getMessage()));
        }
    }

    @PostMapping("/payment/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            Double amount = Double.valueOf(data.get("amount").toString());
            String orderId = service.createRazorpayOrder(amount);
            return ResponseEntity.ok(Map.of("success", true, "orderId", orderId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error creating order: " + e.getMessage()));
        }
    }

    @PostMapping("/payment/success")
    public ResponseEntity<?> updatePaymentSuccess(@RequestBody Map<String, Object> data) {
        try {
            Long registrationId = Long.valueOf(data.get("registrationId").toString());
            String paymentId = data.get("paymentId").toString();
            service.updatePaymentStatus(registrationId, paymentId, "SUCCESS");
            return ResponseEntity.ok(Map.of("success", true, "message", "Payment status updated"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error updating status: " + e.getMessage()));
        }
    }

    @GetMapping("/register/{id}")
    public ResponseEntity<?> getRegistration(@PathVariable("id") Long id) {
        try {
            MarriageRegistration registration = service.getRegistration(id);
            if (registration != null) {
                return ResponseEntity.ok(Map.of("success", true, "data", registration));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Registration not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error fetching details: " + e.getMessage()));
        }
    }

    @GetMapping("/registrations")
    public ResponseEntity<?> getAllRegistrations() {
        try {
            List<MarriageRegistration> list = service.getAllRegistrations();
            return ResponseEntity.ok(Map.of("success", true, "data", list));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error fetching registrations: " + e.getMessage()));
        }
    }

    @PutMapping("/registrations/{id}")
    public ResponseEntity<?> updateRegistration(@PathVariable("id") Long id,
            @RequestBody MarriageRegistration updatedData) {
        try {
            MarriageRegistration updated = service.updateRegistration(id, updatedData);
            if (updated != null) {
                return ResponseEntity
                        .ok(Map.of("success", true, "message", "Registration updated successfully", "data", updated));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Registration not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error updating registration: " + e.getMessage()));
        }
    }

    @PutMapping("/registrations/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> statusData) {
        try {
            String status = statusData.get("status");
            service.updateStatus(id, status);
            return ResponseEntity.ok(Map.of("success", true, "message", "Status updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error updating status: " + e.getMessage()));
        }
    }

    @DeleteMapping("/registrations/{id}")
    public ResponseEntity<?> deleteRegistration(@PathVariable("id") Long id) {
        try {
            boolean deleted = service.deleteRegistration(id);
            if (deleted) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Registration deleted successfully"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Registration not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error deleting registration: " + e.getMessage()));
        }
    }
}
