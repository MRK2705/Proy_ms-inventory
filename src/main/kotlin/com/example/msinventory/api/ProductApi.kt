package com.example.msinventory.api

//import com.example.msinventory.Minio.testMinio
import com.example.msinventory.bl.ProductBl
import com.example.msinventory.dto.ProductDto
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import java.math.BigDecimal
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/product")
class ProductApi (private val productBl: ProductBl){

//    @Autowired
//    lateinit var testMinio: testMinio

    //api de registro de producto
    @PostMapping("/register")
    @CircuitBreaker(name = "productCB", fallbackMethod = "registerProductFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "registerProductFallback")
    fun registerProduct(
        @RequestParam("productName") productName: String,
        @RequestParam("description") description: String,
        @RequestParam("price") price: BigDecimal,
        @RequestParam("image") image: String
    ): ResponseEntity<Any> {
        val productDto = productBl.registerProduct(productName, description, price, image)
        return ResponseEntity.ok(productDto)
    }
    fun registerProductFallback(productName: String, description: String, price: BigDecimal, image: String, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    @GetMapping("/all")
    @CircuitBreaker(name = "productCB", fallbackMethod = "getAllProductsFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "getAllProductsFallback")
    fun getAllProducts(@RequestParam("page") page: Int, @RequestParam("size") size: Int):Any {
        val productsPage: Any = productBl.getAllProducts(page, size)
        return ResponseEntity.ok(productsPage)
    }
    fun getAllProductsFallback(page: Int, size: Int, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    @GetMapping("/getById")
    @CircuitBreaker(name = "productCB", fallbackMethod = "getProductByIdFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "getProductByIdFallback")
    fun getProductById(@RequestParam("id") id: Long): ResponseEntity<ProductDto?> {
        val productDto = productBl.getProductById(id)
        return if (productDto != null) {
            ResponseEntity.ok(productDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    fun getProductByIdFallback(id: Long, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    @PutMapping("/update")
    @CircuitBreaker(name = "productCB", fallbackMethod = "updateProductFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "updateProductFallback")
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
    fun updateProductFallback(id: Long, productName: String, description: String, price: BigDecimal, image: String, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    @DeleteMapping("/delete")
    @CircuitBreaker(name = "productCB", fallbackMethod = "deleteProductFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "deleteProductFallback")
    fun deleteProduct(@RequestParam("id") id: Long): ResponseEntity<Any> {
        productBl.deleteProduct(id)
        return ResponseEntity.ok().build()
    }
    fun deleteProductFallback(id: Long, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    //endpoint para probar conexion con minio
//    @GetMapping("/test")
//    fun testMinio(): ResponseEntity<Any> {
//        val test = testMinio.testMinioConnection()
//        return ResponseEntity.ok(test)
//    }

}