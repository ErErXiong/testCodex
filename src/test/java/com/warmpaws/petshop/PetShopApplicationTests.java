package com.warmpaws.petshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.sql.init.mode=never",
        "pet-shop.schema-initializer.enabled=false",
        "spring.datasource.url=jdbc:mysql://localhost:3306/pet_shop_test",
        "spring.datasource.username=test",
        "spring.datasource.password=test"
})
class PetShopApplicationTests {

    @Test
    void contextLoads() {
    }
}
