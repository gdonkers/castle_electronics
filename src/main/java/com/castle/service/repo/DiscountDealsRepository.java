package com.castle.service.repo;

import com.castle.service.model.DiscountDeal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscountDealsRepository extends CrudRepository<DiscountDeal, DiscountDeal> {

    List<DiscountDeal> findByProductId(String productId);

}
