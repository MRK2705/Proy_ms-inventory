//package com.example.msinventory.config
//
//import io.minio.MinioClient
//import okhttp3.OkHttpClient
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//
//@Configuration
//class MinioConfiguration {
//
//    @Value("\${minio.url.mn}")
//    private val minioUrl: String? = null
//
//    @Value("\${minio.access.key}")
//    private val accessKey: String? = null
//
//    @Value("\${minio.access.secret}")
//    private val secretKey: String? = null
//
//    @Bean
//    @Primary
//    fun minioClient(): MinioClient {
//        println("Minio URL: $minioUrl, Access Key: $accessKey, Secret Key: $secretKey")
//        return MinioClient.Builder()
//                .endpoint(minioUrl)
//                .credentials(accessKey, secretKey)
//                .build()
//    }
//
//}