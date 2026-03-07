package com.ctse.payment_service.service;

import com.ctse.payment_service.dto.PaymentRequest;
import com.ctse.payment_service.model.Payment;

public interface PaymentService {

    Payment processPayment(PaymentRequest request);

    Payment getPaymentById(String id);

}