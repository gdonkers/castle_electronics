package com.castle.service.customer;

import com.castle.service.model.BasketProduct;
import com.castle.service.model.BasketSummary;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/{customer-id}/basket")
    BasketSummary getBasketSummary(@PathVariable("customer-id") String customerId) {

        return service.getBasketSummaryForCustomerWithId(customerId);
    }


    @GetMapping("/{customer-id}/basket/products")
    List<BasketProduct> getAllProductsInBasket(@PathVariable("customer-id") String customerId) {

        return service.getAllProductsInBasketForCustomerWithId(customerId);
    }

    @PostMapping(value = "/{customer-id}/basket/products", consumes = APPLICATION_JSON_VALUE)
    BasketProduct createProduct(@PathVariable("customer-id") String customerId,
                                @RequestBody BasketProduct basketProduct) {

        String basketCustomerId = basketProduct.getCustomerId();
        String idAssertFailure = format("customerId in basket (%s) is not the same as that in path (%s)", basketCustomerId, customerId);
        checkArgument(Objects.equals(basketCustomerId, customerId), idAssertFailure);

        return service.createProductForCustomerId(basketProduct);
    }

    @DeleteMapping(value = "/{customer-id}/basket/products/{product-id}")
    List<BasketProduct> removeProduct(@PathVariable("customer-id") String customerId,
                                      @PathVariable("product-id") String productId) {

        return service.removeProductFromBasketForCustomerIdAndProductId(customerId, productId);
    }
}
