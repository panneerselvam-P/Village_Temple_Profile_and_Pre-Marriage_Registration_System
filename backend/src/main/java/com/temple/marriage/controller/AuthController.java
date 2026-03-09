package com.temple.marriage.controller;

import com.temple.marriage.model.User;
import com.temple.marriage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (userRepository.existsByMobileNumber(user.getMobileNumber())) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Mobile number already registered"));
            }

            // Generate Application Number: TN-XXXX-Random
            String randomPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String appNo = "TN-" + java.time.Year.now().getValue() + "-" + randomPart;
            user.setApplicationNumber(appNo);

            User savedUser = userRepository.save(user);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Registration successful");
            response.put("applicationNumber", savedUser.getApplicationNumber());
            response.put("user", savedUser);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error registering user: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String appNo = loginData.get("applicationNo");
        String mobile = loginData.get("mobile");

        return userRepository.findByApplicationNumberAndMobileNumber(appNo, mobile)
                .map(user -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("message", "Login successful");
                    response.put("data", Map.of("user", user, "token", UUID.randomUUID().toString())); // Dummy token
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Invalid Application Number or Mobile Number")));
    }
}
