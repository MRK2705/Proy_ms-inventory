package com.example.msinventory.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.sql.Timestamp

data class ProductDto (
    var productName: String?,
    var description: String?,
    var price: BigDecimal?,
    var image: String?,

) {
    constructor() : this(null, null, null,null)

    override fun toString(): String {
        return "ProductDto(productName=$productName, description=$description, price=$price, image=$image)"
    }
}
