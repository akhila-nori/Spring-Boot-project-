package com.module2.module2;

import com.module2.module2.entities.ProductEntity;
import com.module2.module2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class Module2ApplicationTests {

    @Autowired  //injecting ProductRepository bean here
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository(){
        //creating a new product entity object with these fields --> using Builder design pattern

        //Hibernate creates an insert query and insert this ProductEntity into database

        ProductEntity productEntity = ProductEntity.builder()
                .sku("nestle234")
                .title("Nestle Chcoalter")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12).build();

       ProductEntity savedProductEntity =  productRepository.save(productEntity);
        System.out.println(savedProductEntity);

    }

    @Test
    //it starts mock server
    void getRepository() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        System.out.println(productEntityList);


    }

}
