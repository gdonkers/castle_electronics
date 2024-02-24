package com.castle.cucumber;

import com.castle.service.repo.BasketProductsRepository;
import com.castle.service.repo.DiscountDealsRepository;
import com.castle.service.repo.ProductRepository;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class, webEnvironment = RANDOM_PORT)
public class SpringCucumberTestBase {
    @LocalServerPort
    int port;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DiscountDealsRepository discountDealsRepository;
    @Autowired
    BasketProductsRepository basketProductsRepository;
}
