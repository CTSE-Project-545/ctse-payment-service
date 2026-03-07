package com.ctse.payment_service.controller;

import com.ctse.payment_service.dto.PaymentRequest;
import com.ctse.payment_service.model.Payment;
import com.ctse.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPayment(@PathVariable String id) {
        Payment payment = paymentService.getPaymentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Payment successful");
        response.put("payment", payment);
        return response;
    }
}