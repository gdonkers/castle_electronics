package com.castle.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketSummary {
    private Double totalPrice;
    private String customerId;
    private List<BasketProductSummary> products;
}
