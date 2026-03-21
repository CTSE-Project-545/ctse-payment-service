package com.ctse.payment_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank
    private String cardHolderName;

    @NotBlank
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvv;

    @Positive
    private Double amount;

    @NotBlank
    private String currency;

    @NotBlank
    private String orderId;
}