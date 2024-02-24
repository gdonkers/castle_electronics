package com.castle.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "basket_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct {

    @Id
    private String id;
    private String productId;
    private String customerId;
    private int quantity;
}
