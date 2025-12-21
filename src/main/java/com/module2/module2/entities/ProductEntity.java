package com.module2.module2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data   //comes from lombok for getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Builder  //to use builder design pattern
@Entity  //This is entity
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames={"sku"}),
                @UniqueConstraint(name = "title_price_unique", columnNames = {"title_x", "price"}) // 2 records cannot have same price + title combination
        },
        indexes = {
                @Index(name = "sku_index", columnList = "sku")
        }

)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Hibernate will create auto-generated sql code, with autogeneration of Id
    private Long id;

    @Column(nullable = false, length=20) //instruction to Hibernate in database , Database Schema: When Hibernate automatically creates your table (because of ddl-auto=update), it adds a NOT NULL constraint to the SQL column.
    //Data Integrity: It ensures that you cannot save an object to the database if the sku field is empty (null). If you try, the database will throw an error and reject the save operation.
    private String sku;

    @Column(name="title_x")
    private String title;
    private BigDecimal price;
    private int quantity;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
