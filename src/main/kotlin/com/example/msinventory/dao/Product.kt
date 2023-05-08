package com.example.msinventory.dao

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
class Product (
    var productName: String,
    var description: String,
    var price: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var productId: Long = 0,
) {
    constructor() : this("","", BigDecimal.ZERO, 0)
}