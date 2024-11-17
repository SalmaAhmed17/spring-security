package com.example.springSecurity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authorize")
public class userController {

    @GetMapping("/USER-role")
    public ResponseEntity<Map<String, Object>> userInfo(Authentication authentication) {
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

        Map<String, Object> response = new HashMap<>();
        if (isUser) {
            response.put("message", "Welcome " + authentication.getName() + ", you are a USER.");
            response.put("status", "SUCCESS");
            return ResponseEntity.ok(response);
        } else if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))){
            response.put("message", "You are not authorized to access this route");
            //response.put("status", "UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return null;
    }

    @GetMapping("/ADMIN-role")
    public ResponseEntity<Map<String, Object>> adminInfo(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        Map<String, Object> response = new HashMap<>();
        if (isAdmin) {
            response.put("message", "Welcome " + authentication.getName() + ", you are an ADMIN.");
            response.put("status", "SUCCESS");
            return ResponseEntity.ok(response);
        } else if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))){
            response.put("message", "You are not authorized to access this route");
           // response.put("status", "UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return null;
    }
}
