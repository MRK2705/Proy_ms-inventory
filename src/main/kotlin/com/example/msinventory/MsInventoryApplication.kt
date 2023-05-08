package com.example.msinventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MsInventoryApplication

fun main(args: Array<String>) {
	runApplication<MsInventoryApplication>(*args)
}