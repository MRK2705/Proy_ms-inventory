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
    fun registerProduct(@RequestParam productName: String, @RequestParam description: String, @RequestParam price: BigDecimal): ResponseEntity<Any> {
        val productDto = productBl.registerProduct(productName, description, price)
        return ResponseEntity.ok(productDto)
    }
    @GetMapping("/all")
    fun getAllProducts(pageable: Pageable): ResponseEntity<Page<ProductDto>> {
        val productsPage: Page<ProductDto> = productBl.getAllProducts(pageable)
        return ResponseEntity.ok(productsPage)
    }
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val productDto: ProductDto = productBl.getProductById(id)
        return ResponseEntity.ok(productDto)
    }
    @PutMapping("/update/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestParam productName: String?, @RequestParam description: String?, @RequestParam price: BigDecimal?): ResponseEntity<ProductDto> {
        val updatedProduct: ProductDto = productBl.updateProduct(id, productName, description, price)
        return ResponseEntity.ok(updatedProduct)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        productBl.deleteProduct(id)
    }


}