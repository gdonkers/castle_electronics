package com.castle.service.repo;

import com.castle.service.model.BasketProduct;
import com.castle.service.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketProductsRepository extends CrudRepository<BasketProduct, String> {

    List<BasketProduct> findByCustomerId(String id);

}
