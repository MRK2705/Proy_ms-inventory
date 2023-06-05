//package com.example.msinventory.Minio
//
//import io.minio.*
//import org.slf4j.LoggerFactory
//import org.springframework.stereotype.Component
//import org.springframework.util.StringUtils
//import org.springframework.web.multipart.MultipartFile
//import java.util.*
//
//@Component
//class UploadLogs{
//
//    private lateinit var minioClient: MinioClient
//
//    private val logger = LoggerFactory.getLogger(UploadLogs::class.java)
//
//    fun uploadFileMinio(file: MultipartFile): String {
//        logger.info("BL: Iniciando proceso para guardar archivo en Minio")
//        //Normalizamos el nombre
//        val fileName = StringUtils.cleanPath(file.originalFilename!!)
//        logger.info("BL: Nombre del archivo: $fileName")
//        //Revisar si el nombre del archivo contiene caracteres invalidos
//        if (fileName.contains("..")) {
//            logger.error("BL: Nombre de archivo invalido $fileName")
//            throw RuntimeException("BL: Nombre de archivo invalido $fileName")
//        }
//
//        val contentType = if (file.contentType == null) {
//            "application/octet-stream" //Si no se especifica el tipo de archivo, se asigna por defecto
//        } else {
//            file.contentType
//        }
//
//        val fileExtension = fileName.substringAfterLast(".") //Obtenemos la extension del archivo
//        logger.info("BL: Tipo de archivo: $fileExtension, Content-Type: $contentType")
//        val currentDate = Date()
//
//        //Generamos el nombre del archivo
//        val newFileName = "${currentDate.time}.$fileExtension"
//        logger.info("BL: Nombre del archivo generado: $newFileName")
//
//        //Subimos el archivo a Minio
//        val bucketName = "logssoftware"
//        logger.info("BL: Subiendo archivo a Minio")
//        val putObjectArgs = PutObjectArgs.builder()
//                .bucket(bucketName)
//                .`object`(newFileName)
//                .stream(file.inputStream, file.size.toLong(), -1)
//                .contentType(contentType)
//                .build()
//        minioClient.putObject(putObjectArgs)
//        logger.info("BL: Archivo subido a Minio")
//
//        return newFileName
//    }
//
//}