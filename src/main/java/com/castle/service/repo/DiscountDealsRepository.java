package com.castle.service.repo;

import com.castle.service.model.DiscountDeal;
import com.castle.service.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscountDealsRepository extends CrudRepository<DiscountDeal, String> {

    List<DiscountDeal> findByProductId(String productId);

}
