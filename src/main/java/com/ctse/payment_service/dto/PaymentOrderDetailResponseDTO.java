package com.ctse.payment_service.dto;

import com.ctse.payment_service.model.Payment;

public class PaymentOrderDetailResponseDTO {

    private Payment payment;
    private OrderDetailResponseDTO order;

    public PaymentOrderDetailResponseDTO() {
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderDetailResponseDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDetailResponseDTO order) {
        this.order = order;
    }
}
