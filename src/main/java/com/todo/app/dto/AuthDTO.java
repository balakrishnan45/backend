package com.todo.app.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTO {

    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 3, max = 20)
        private String username;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 6, max = 40)
        private String password;
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @Data
    public static class JwtResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String username;
        private String email;

        public JwtResponse(String token, Long id, String username, String email) {
            this.token = token;
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    @Data
    public static class MessageResponse {
        private String message;
        public MessageResponse(String message) {
            this.message = message;
        }
    }
}
