package com.BankingService.Cards.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private  String apiPath;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;
}
