package com.example.backend.restaurante.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
