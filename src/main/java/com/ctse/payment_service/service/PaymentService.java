package com.ctse.payment_service.service;

import com.ctse.payment_service.dto.PaymentRequest;
import com.ctse.payment_service.dto.PaymentOrderDetailResponseDTO;
import com.ctse.payment_service.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment processPayment(PaymentRequest request);

    Payment getPaymentById(String id);

    List<Payment> getAllPayments();

    Payment updatePaymentById(String id, PaymentRequest request);

    void deletePaymentById(String id);

    List<PaymentOrderDetailResponseDTO> getPaymentDetailsWithOrder();

}