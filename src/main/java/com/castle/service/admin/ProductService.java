package com.castle.service.admin;

import com.castle.service.model.DiscountDeal;
import com.castle.service.model.DiscountDealType;
import com.castle.service.model.IdGenerator;
import com.castle.service.model.Product;
import com.castle.service.repo.DiscountDealsRepository;
import com.castle.service.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class ProductService {
    private final DiscountDealsRepository discountDealRepo;
    private final ProductRepository productRepo;
    private final IdGenerator idGenerator;

    List<Product> getAllProducts() {
        return newArrayList(productRepo.findAll());
    }

    Product createProduct(Product product) {
        product.setId(ofNullable(product.getId()).orElseGet(idGenerator::createId));

        productRepo.save(product);

        return product;
    }

    void removeProductById(String id) {
        productRepo.deleteById(id);
    }

    void removeDealFromProductWithId(String productId, DiscountDealType discountDealType) {

        DiscountDeal deal = DiscountDeal
                .builder()
                .productId(productId)
                .dealType(discountDealType)
                .build();

        discountDealRepo.delete(deal);
    }

    DiscountDeal addDealWithProductId(String productId, DiscountDealType discountDealType) {
        productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("There is product with id " + productId));

        DiscountDeal deal = DiscountDeal
                .builder()
                .productId(productId)
                .dealType(discountDealType)
                .build();
        discountDealRepo.save(deal);

        return deal;
    }

    List<DiscountDeal> getAllDealsFromProductWithId(String productId) {
        return discountDealRepo.findByProductId(productId);
    }
}
