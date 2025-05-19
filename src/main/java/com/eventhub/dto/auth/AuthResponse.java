package com.eventhub.dto.auth;

import com.eventhub.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
} 