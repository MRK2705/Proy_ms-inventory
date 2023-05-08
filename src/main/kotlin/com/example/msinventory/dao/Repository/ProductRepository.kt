package com.example.msinventory.dao.Repository

import com.example.msinventory.dao.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long>