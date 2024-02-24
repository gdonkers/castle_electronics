package com.castle.service.model;

import com.codepoetics.protonpack.Indexed;

import java.util.List;

import static com.codepoetics.protonpack.StreamUtils.zipWithIndex;

public enum DiscountDealType {
    Buy_1_get_50_pct_off_second(new Buy1Get50PctOffSecondDiscountCalculator()),
    Get_10_pct_off(new Get10PctOffDiscountCalculator());

    private final DiscountCalculator calculator;

    DiscountDealType(DiscountCalculator calculator) {
        this.calculator = calculator;
    }

    public List<ProductPriceUnit> adjustUnitPrices(List<ProductPriceUnit> priceUnits) {
        return calculator.calculateDiscount(priceUnits);
    }

    public static class Buy1Get50PctOffSecondDiscountCalculator implements DiscountCalculator {
        @Override
        public List<ProductPriceUnit> calculateDiscount(List<ProductPriceUnit> priceUnits) {
            return zipWithIndex(priceUnits.stream())
                    .map(Buy1Get50PctOffSecondDiscountCalculator::adjustUnitPrice)
                    .toList();
        }

        private static ProductPriceUnit adjustUnitPrice(Indexed<ProductPriceUnit> unit) {
            ProductPriceUnit priceUnit = unit.getValue();
            if ((unit.getIndex() + 1) % 2 == 0) {
                return new ProductPriceUnit(0.5 * priceUnit.getPrice());
            }
            return priceUnit;
        }
    }

    public static class Get10PctOffDiscountCalculator implements DiscountCalculator {
        @Override
        public List<ProductPriceUnit> calculateDiscount(List<ProductPriceUnit> priceUnits) {
            return priceUnits.stream().map(unit -> new ProductPriceUnit(unit.getPrice() * 0.9)).toList();
        }
    }
}
