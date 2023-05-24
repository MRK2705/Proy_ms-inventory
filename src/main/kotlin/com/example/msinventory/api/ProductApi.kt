package com.example.msinventory.api

import com.example.msinventory.bl.ProductBl
import com.example.msinventory.dto.ProductDto
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import java.math.BigDecimal
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/product")
class ProductApi (private val productBl: ProductBl){

    //api de registro de producto
    @PostMapping("/register")
    fun registerProduct(
        @RequestParam("productName") productName: String,
        @RequestParam("description") description: String,
        @RequestParam("price") price: BigDecimal,
        @RequestParam("image") image: String
    ): ResponseEntity<Any> {
        val productDto = productBl.registerProduct(productName, description, price, image)
        return ResponseEntity.ok(productDto)
    }

    @GetMapping("/all")
    fun getAllProducts(@RequestParam("page") page: Int, @RequestParam("size") size: Int):Any {
        val productsPage: Any = productBl.getAllProducts(page, size)
        return ResponseEntity.ok(productsPage)
    }
    @GetMapping("/getById")
    fun getProductById(@RequestParam("id") id: Long): ResponseEntity<ProductDto?> {
        val productDto = productBl.getProductById(id)
        return if (productDto != null) {
            ResponseEntity.ok(productDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/update")
    fun updateProduct(
        @RequestParam("id") id: Long,
        @RequestParam("productName") productName: String,
        @RequestParam("description") description: String,
        @RequestParam("price") price: BigDecimal,
        @RequestParam("image") image: String
    ): ResponseEntity<ProductDto> {
        val updatedProductDto = productBl.updateProduct(id, productName, description, price, image)
        return if (updatedProductDto != null) {
            ResponseEntity.ok(updatedProductDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/delete")
    fun deleteProduct(@RequestParam("id") id: Long): ResponseEntity<Any> {
        productBl.deleteProduct(id)
        return ResponseEntity.ok().build()
    }


}