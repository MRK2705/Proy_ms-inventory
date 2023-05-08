package com.example.msinventory.api

import com.example.msinventory.bl.ProductBl
import com.example.msinventory.dto.ProductDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("api/v1/product")
class ProductApi (private val productBl: ProductBl){

    //api de registro de producto
    @PostMapping("/register")
    fun registerProduct(@RequestParam productName: String, @RequestParam description: String, @RequestParam price: BigDecimal): ResponseEntity<Any> {
        val productDto = productBl.registerProduct(productName, description, price)
        return ResponseEntity.ok(productDto)
    }

}