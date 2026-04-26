package com.ctse.payment_service.service;

import com.ctse.payment_service.client.OrderClient;
import com.ctse.payment_service.dto.OrderDetailResponseDTO;
import com.ctse.payment_service.dto.PaymentOrderDetailResponseDTO;
import com.ctse.payment_service.dto.PaymentRequest;
import com.ctse.payment_service.model.Payment;
import com.ctse.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

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
                .orderId(request.getOrderId())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public List<PaymentOrderDetailResponseDTO> getPaymentDetailsWithOrder() {
        List<OrderDetailResponseDTO> orders = orderClient.getOrderDetails();
        List<Payment> payments = paymentRepository.findAll();

        Map<String, List<Payment>> paymentsByOrderId = payments.stream()
                .collect(Collectors.groupingBy(Payment::getOrderId));

        List<PaymentOrderDetailResponseDTO> result = new ArrayList<>();

        for (OrderDetailResponseDTO order : orders) {
            List<Payment> orderPayments = paymentsByOrderId.getOrDefault(order.getId(), Collections.emptyList());
            for (Payment payment : orderPayments) {
                PaymentOrderDetailResponseDTO dto = new PaymentOrderDetailResponseDTO();
                dto.setOrder(order);
                dto.setPayment(payment);
                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public Payment getPaymentById(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePaymentById(String id, PaymentRequest request) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existingPayment.setCardHolderName(request.getCardHolderName());
        existingPayment.setCardNumber(maskCardNumber(request.getCardNumber()));
        existingPayment.setAmount(request.getAmount());
        existingPayment.setCurrency(request.getCurrency());
        existingPayment.setPaymentDate(LocalDateTime.now());
        existingPayment.setCvv(request.getCvv());
        existingPayment.setOrderId(request.getOrderId());

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePaymentById(String id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    private String maskCardNumber(String cardNumber) {
        return "****-****-****-" + cardNumber.substring(12);
    }
}