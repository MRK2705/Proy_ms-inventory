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
import org.springframework.data.domain.PageRequest

@Service
class ProductBl @Autowired constructor(private val productRepository: ProductRepository) {

    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(ProductBl::class.java.name)
    }

    // Función para registrar un producto
    fun registerProduct(productName: String, description: String, price: BigDecimal, image: String): ProductDto {
        LOGGER.info("Registrando producto")
        val product: Product = Product()
        product.productName = productName
        product.description = description
        product.price = price
        product.image = image
        productRepository.save(product)
        LOGGER.info("Producto guardado en base de datos")
        val productDto = ProductDto(product.id, product.productName, product.description, product.price, product.image)
        return productDto
    }

    // Función para obtener un producto por su ID
    fun getProductById(id: Long): ProductDto? {
        LOGGER.info("Obteniendo producto por ID")
        val product = productRepository.findById(id).orElse(null)
        return ProductDto(product.id, product.productName, product.description, product.price, product.image)

    }
    // Función para obtener todos los productos
    fun getAllProducts(page: Int, size: Int): Any {
        LOGGER.info("Obteniendo todos los productos paginados")
        val productsPage = productRepository.findAll(PageRequest.of(page, size))
        return productsPage
    }

    // Función para actualizar un producto
    fun updateProduct(id: Long, productName: String, description: String, price: BigDecimal, image: String): ProductDto? {
        LOGGER.info("Actualizando producto")
        val product = productRepository.findById(id).orElse(null)
        if (product != null) {
            product.productName = productName
            product.description = description
            product.price = price
            product.image = image
            productRepository.save(product)
            LOGGER.info("Producto actualizado en base de datos")
            return ProductDto(product.id, product.productName, product.description, product.price, product.image)
        }
        return null
    }

    // Función para eliminar un producto
    fun deleteProduct(id: Long) {
        LOGGER.info("Eliminando producto")
        productRepository.deleteById(id)
        LOGGER.info("Producto eliminado de la base de datos")
    }

}
