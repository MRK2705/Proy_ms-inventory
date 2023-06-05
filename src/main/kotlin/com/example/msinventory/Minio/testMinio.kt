//package com.example.msinventory.Minio
//
//import io.minio.MinioClient
//import io.minio.errors.MinioException
//import io.minio.BucketExistsArgs
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Component
//
//@Component
//class testMinio {
//
//    @Value("\${minio.access-key}")
//    private val accessKey: String? = null
//
//    @Value("\${minio.secret-key}")
//    private val secretKey: String? = null
//
//    @Value("\${minio.url}")
//    private val minioUrl: String? = null
//
//    @Value("\${minio.bucket}")
//    private val bucket: String? = null
//
//    fun testMinioConnection() {
//        try {
//            // Crear una instancia de MinioClient
//            val minioClient = MinioClient.builder()
//                    .endpoint(minioUrl)
//                    .credentials(accessKey, secretKey)
//                    .build()
//
//            // Verificar la conexión
//            val bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())
//            if (bucketExists) {
//                println("Conexión exitosa con MinIO")
//            } else {
//                println("El bucket no existe en MinIO")
//            }
//        } catch (e: MinioException) {
//            println("Error al conectar con MinIO: ${e.message}")
//        }
//    }
//
//}