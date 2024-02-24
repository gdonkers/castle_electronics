package com.castle.service.admin;

import com.castle.service.model.DiscountDeal;
import com.castle.service.model.DiscountDealType;
import com.castle.service.model.IdGenerator;
import com.castle.service.model.Product;
import com.castle.service.repo.DiscountDealsRepository;
import com.castle.service.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final DiscountDealsRepository discountDealRepo;
    private final ProductRepository productRepo;
    private final IdGenerator idGenerator;

    @GetMapping
    List<Product> getAllProducts() {
        return newArrayList(productRepo.findAll());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    Product createProduct(@RequestBody Product product) {
        product.setId(idGenerator.createId());
        productRepo.save(product);

        return product;
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> removeProduct(@PathVariable String id) {
        productRepo.deleteById(id);
        return ok().build();
    }

    @DeleteMapping(value = "/discount-deals/{deal-id}")
    ResponseEntity<Void> removeDeal(@PathVariable("deal-id") String dealId) {

        discountDealRepo.deleteById(dealId);

        return ok().build();
    }

    @PostMapping(value = "/{product-id}/discount-deals/{deal-type}")
    DiscountDeal addDeal(@PathVariable("product-id") String productId,
                         @PathVariable("deal-type") DiscountDealType discountDealType) {
        DiscountDeal deal = DiscountDeal
                .builder()
                .id(idGenerator.createId())
                .productId(productId)
                .dealType(discountDealType)
                .build();
        discountDealRepo.save(deal);

        return deal;
    }

    @GetMapping("/{product-id}/discount-deals")
    List<DiscountDeal> getAllDealsFromProductWithId(@PathVariable("product-id") String productId) {
        return discountDealRepo.findByProductId(productId);
    }
}
