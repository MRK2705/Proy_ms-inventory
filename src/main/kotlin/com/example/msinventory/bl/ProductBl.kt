package com.example.msinventory.bl

import com.example.msinventory.dao.Product
import com.example.msinventory.dao.Repository.ProductRepository
import com.example.msinventory.dto.ProductDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.FormBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value

@Service
class ProductBl @Autowired constructor(private val productRepository: ProductRepository ){

    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(ProductBl::class.java.name)
    }

    //funcion para registrar un producto
    fun registerProduct(productName: String, description: String, price: BigDecimal, image: String): ProductDto {
        LOGGER.info("Registrando producto")
        val product: Product = Product()
        product.productName = productName
        product.description = description
        product.price = price
        product.image = image
        productRepository.save(product)
        LOGGER.info("Producto guardado en base de datos")
        val productDto = ProductDto(product.productName, product.description, product.price,product.image)
        return productDto
    }
    fun getAllProducts(pageable: Pageable): Page<ProductDto> {
        LOGGER.info("Obteniendo todos los productos paginados")
        val productsPage: Page<Product> = productRepository.findAll(pageable)
        return productsPage.map { product -> ProductDto(product.productName, product.description, product.price,product.image) }
    }
    fun getProductById(productId: Long): ProductDto {
        LOGGER.info("Obteniendo producto por ID: $productId")
        val product: Product = productRepository.findById(productId).orElseThrow { NoSuchElementException("Producto no encontrado") }
        return ProductDto(product.productName, product.description, product.price,product.image)
    }
    fun updateProduct(productId: Long, productName: String?, description: String?, price: BigDecimal?,image:String?): ProductDto {
        LOGGER.info("Actualizando producto con ID: $productId")
        val product: Product = productRepository.findById(productId).orElseThrow { NoSuchElementException("Producto no encontrado") }
        productName?.let { product.productName = it }
        description?.let { product.description = it }
        price?.let { product.price = it }
        image?.let { product.image = it }
        productRepository.save(product)
        return ProductDto(product.productName, product.description, product.price,product.image)
    }
    fun deleteProduct(productId: Long) {
        LOGGER.info("Eliminando producto con ID: $productId")
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId)
            LOGGER.info("Producto eliminado")
        } else {
            throw NoSuchElementException("Producto no encontrado")
        }
    }




}
