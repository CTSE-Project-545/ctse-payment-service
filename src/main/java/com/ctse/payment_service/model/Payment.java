package com.ctse.payment_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String paymentId;

    private String cardHolderName;

    private String cardNumber;

    private Double amount;

    private String currency;

    private LocalDateTime paymentDate;

    private String cvv;

    private String orderId;

}