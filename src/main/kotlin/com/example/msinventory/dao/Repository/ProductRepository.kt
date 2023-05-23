package com.example.msinventory.dao.Repository

import com.example.msinventory.dao.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository: PagingAndSortingRepository<Product, Long>

//interface ProductRepository: CrudRepository<Product, Long>