package com.castle.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "discount_deals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDeal {

    @Id
    private String id;
    private String productId;
    private DiscountDealType dealType;
}
