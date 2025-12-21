package com.module2.module2.repository;


import com.module2.module2.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository //(inside this annotation we have Component annotation) --> this will create a bean of ProductRepository to be used injected and used by some other Java class
//JpaRepository interface implements --> SimpleJpaRepository class (in-built comes in java with spting-data-jpa dependency included in pom.xml)
//SimpleJpaRepository class already has all methods implemented of this interface
//we don't have to do anything --> this is Abstraction
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreateAtAfter(LocalDateTime after);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select from ProductEntity e where e.title=?1 and e.price=?2") //has to be as per java class name and java objects
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
}
