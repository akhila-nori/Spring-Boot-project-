package com.module2.module2;

import com.module2.module2.entities.ProductEntity;
import com.module2.module2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        List<ProductEntity> productEntityList1 = productRepository.findByTitle("nestle");
        List<ProductEntity> productEntityList2 = productRepository.findByCreateAtAfter(LocalDateTime.of(2026,1,1,0,0,0));
        System.out.println(productEntityList2);
    }

    @Test
    void getSingleFromRepository(){
        Optional<ProductEntity> productEntityOptional = productRepository.findByTitleAndPrice("Nestle Chocoalet",BigDecimal.valueOf(23.45));

        productEntityOptional.ifPresent(System.out::println);


    }

}
