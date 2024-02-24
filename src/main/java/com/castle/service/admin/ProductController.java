package com.castle.service.admin;

import com.castle.service.model.DiscountDeal;
import com.castle.service.model.DiscountDealType;
import com.castle.service.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    Product createProduct(@RequestBody Product product) {

        return service.createProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> removeProduct(@PathVariable String id) {
        service.removeProductById(id);

        return ok().build();
    }

    @DeleteMapping(value = "/{product-id}/discount-deals/{deal-type}")
    ResponseEntity<Void> removeDeal(@PathVariable("product-id") String productId,
                                    @PathVariable("deal-type") DiscountDealType discountDealType) {

        service.removeDealFromProductWithId(productId, discountDealType);

        return ok().build();
    }

    @PostMapping(value = "/{product-id}/discount-deals/{deal-type}")
    DiscountDeal addDeal(@PathVariable("product-id") String productId,
                         @PathVariable("deal-type") DiscountDealType discountDealType) {

        return service.addDealWithProductId(productId, discountDealType);
    }

    @GetMapping("/{product-id}/discount-deals")
    List<DiscountDeal> getAllDealsFromProductWithId(@PathVariable("product-id") String productId) {

        return service.getAllDealsFromProductWithId(productId);
    }
}
