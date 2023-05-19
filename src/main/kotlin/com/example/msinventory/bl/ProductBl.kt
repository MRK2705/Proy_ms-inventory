package com.example.msinventory.bl

import com.example.msinventory.dao.Product
import com.example.msinventory.dao.Repository.ProductRepository
import com.example.msinventory.dto.ProductDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.FormBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
    fun registerProduct(productName: String, description: String, price: BigDecimal): ProductDto {
        LOGGER.info("Registrando producto")
        val product: Product = Product()
        product.productName = productName
        product.description = description
        product.price = price
        productRepository.save(product)
        LOGGER.info("Producto guardado en base de datos")
        val productDto = ProductDto(product.productName, product.description, product.price)
        return productDto
    }

}
