package com.castle.service.customer;

import com.castle.service.model.*;
import com.castle.service.repo.BasketProductsRepository;
import com.castle.service.repo.DiscountDealsRepository;
import com.castle.service.repo.ProductRepository;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.IntStream.range;

@Service
@AllArgsConstructor
public class CustomerService {
    private final DiscountDealsRepository discountDealRepo;
    private final ProductRepository productRepo;
    private final BasketProductsRepository basketProductsRepo;
    private final IdGenerator idGenerator;

    BasketSummary getBasketSummaryForCustomerWithId(String customerId) {
        List<BasketProduct> products = basketProductsRepo.findByCustomerId(customerId);

        List<BasketProductSummary> productSummaries = products
                .stream()
                .map(this::toProductSummary)
                .toList();
        Double totalBasketPrice = productSummaries
                .stream()
                .map(BasketProductSummary::getTotalPriceAfterDiscounts)
                .reduce(0.0, Double::sum);
        return BasketSummary
                .builder()
                .customerId(customerId)
                .totalPrice(totalBasketPrice)
                .products(productSummaries)
                .build();
    }

    private BasketProductSummary toProductSummary(BasketProduct basketProduct) {
        String productId = basketProduct.getProductId();
        Product product = lookupProductWithId(productId);
        List<DiscountDealType> discounts = lookupDiscountDealsForProductWithId(productId);
        Double priceAfterDiscount = calculatePriceAfterDiscount(basketProduct, product, discounts);
        return BasketProductSummary
                .builder()
                .name(product.getName())
                .originalUnitPrice(product.getUnitPrice())
                .totalPriceAfterDiscounts(priceAfterDiscount)
                .quantity(basketProduct.getQuantity())
                .discounts(discounts)
                .build();
    }

    private Product lookupProductWithId(String productId) {
        return productRepo
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("This product has been discontinued"));
    }

    @VisibleForTesting
    Double calculatePriceAfterDiscount(BasketProduct basketProduct, Product product, List<DiscountDealType> discountDeals) {
        List<ProductPriceUnit> priceUnits = range(0, basketProduct.getQuantity())
                .mapToObj(i -> new ProductPriceUnit(product.getUnitPrice()))
                .toList();
        for (DiscountDealType discount : discountDeals) {
            priceUnits = discount.adjustUnitPrices(priceUnits);
        }
        return priceUnits.stream().map(ProductPriceUnit::getPrice).reduce(0.0, Double::sum);
    }

    private List<DiscountDealType> lookupDiscountDealsForProductWithId(String productId) {
        return discountDealRepo
                .findByProductId(productId)
                .stream()
                .map(DiscountDeal::getDealType)
                .toList();
    }

    List<BasketProduct> getAllProductsInBasketForCustomerWithId(String customerId) {
        return basketProductsRepo.findByCustomerId(customerId);
    }

    BasketProduct createProductForCustomerId(BasketProduct basketProduct) {
        basketProduct.setId(idGenerator.createId());
        basketProductsRepo.save(basketProduct);

        return basketProduct;
    }

    List<BasketProduct> removeProductFromBasketForCustomerIdAndProductId(String customerId, String productId) {
        List<BasketProduct> toDelete = basketProductsRepo
                .findByCustomerId(customerId)
                .stream()
                .filter(p -> productId.equals(p.getProductId()))
                .toList();

        basketProductsRepo.deleteAll(toDelete);

        return toDelete;
    }
}
