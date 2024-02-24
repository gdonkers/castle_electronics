package com.castle.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "discount_deals")
@IdClass(DiscountDeal.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDeal implements Serializable {

    @Id
    private String productId;
    @Id
    private DiscountDealType dealType;
}
