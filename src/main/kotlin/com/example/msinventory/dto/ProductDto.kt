package com.example.msinventory.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.sql.Timestamp

data class ProductDto (
    var productName: String?,
    var description: String?,
    var price: BigDecimal?,
    @JsonIgnore
    var timestamp: Timestamp? = null,
    @JsonIgnore
    var status: String? = null,
    @JsonIgnore
    var error: String? = null,
    @JsonIgnore
    var path: String? = null

) {
    constructor() : this(null, null, null)

    override fun toString(): String {
        return "ProductDto(productName=$productName, description=$description, price=$price)"
    }
}