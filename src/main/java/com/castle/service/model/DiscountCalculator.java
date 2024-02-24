package com.castle.service.model;

import java.util.List;

public interface DiscountCalculator {
    List<ProductPriceUnit> calculateDiscount(List<ProductPriceUnit> priceUnits);
}
