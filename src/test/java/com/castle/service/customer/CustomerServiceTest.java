package com.castle.service.customer;

import com.castle.service.model.BasketProduct;
import com.castle.service.model.DiscountDealType;
import com.castle.service.model.Product;
import org.junit.Test;

import java.util.List;

import static com.castle.service.model.DiscountDealType.Buy_1_get_50_pct_off_second;
import static com.castle.service.model.DiscountDealType.Get_10_pct_off;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerServiceTest {
    private final CustomerService service = new CustomerService(null, null, null, null);

    @Test
    public void total_price_after_no_discounts() {
        List<DiscountDealType> discounts = List.of();

        assertThat(totalPrice(0, 1.0, service, discounts)).isEqualTo(0.0);
        assertThat(totalPrice(2, 1_000.0, service, discounts)).isEqualTo(2_000.0);
        assertThat(totalPrice(3, 1_000.0, service, discounts)).isEqualTo(3_000.0);
    }
    @Test
    public void total_price_after_discount_for_Buy_1_get_50_pct_off_second() {
        List<DiscountDealType> discounts = List.of(Buy_1_get_50_pct_off_second);

        assertThat(totalPrice(0, 1.0, service, discounts)).isEqualTo(0.0);
        assertThat(totalPrice(2, 1_000.0, service, discounts)).isEqualTo(1_500.0);
        assertThat(totalPrice(3, 1_000.0, service, discounts)).isEqualTo(2_500.0);
    }

    @Test
    public void total_price_after_multiple_discounts() {
        List<DiscountDealType> discounts = List.of(Buy_1_get_50_pct_off_second, Get_10_pct_off);

        assertThat(totalPrice(0, 1.0, service, discounts)).isEqualTo(0.0);
        assertThat(totalPrice(2, 1_000.0, service, discounts)).isEqualTo(1_350.0);
        assertThat(totalPrice(3, 1_000.0, service, discounts)).isEqualTo(2_250.0);
    }

    private static Double totalPrice(int quantity, Double unitPrice, CustomerService service, List<DiscountDealType> discounts) {
        BasketProduct basketProduct = BasketProduct.builder().quantity(quantity).build();
        Product product = Product.builder().unitPrice(unitPrice).build();

        return service.calculatePriceAfterDiscount(basketProduct, product, discounts);
    }
}
