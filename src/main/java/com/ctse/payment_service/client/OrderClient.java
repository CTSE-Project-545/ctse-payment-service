package com.ctse.payment_service.client;

import com.ctse.payment_service.dto.OrderDetailResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {

    @GetMapping("/api/orders/orderDetails")
    List<OrderDetailResponseDTO> getOrderDetails();
}
