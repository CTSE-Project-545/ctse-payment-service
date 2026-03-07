package com.ctse.payment_service.service;

import com.ctse.payment_service.dto.PaymentRequest;
import com.ctse.payment_service.model.Payment;
import com.ctse.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment processPayment(PaymentRequest request) {

        // Mask card number
        String maskedCard = maskCardNumber(request.getCardNumber());

        // Simulate payment success (you can add logic here)
        Payment payment = Payment.builder()
            .cardHolderName(request.getCardHolderName())
            .cardNumber(maskedCard)
            .amount(request.getAmount())
            .currency(request.getCurrency())
            .paymentDate(LocalDateTime.now())
            .cvv(request.getCvv())
            .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(String id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        return payment;
    }

    private String maskCardNumber(String cardNumber) {
        return "****-****-****-" + cardNumber.substring(12);
    }
}