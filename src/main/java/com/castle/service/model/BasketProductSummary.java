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
public class BasketProductSummary {
    private String name;
    private int quantity;
    private Double originalUnitPrice;
    private Double totalPriceAfterDiscounts;
    private List<DiscountDealType> discounts;
}
