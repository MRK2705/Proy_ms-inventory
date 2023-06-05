//package com.example.msinventory.api
//
//import com.example.msinventory.Minio.UploadLogs
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//import org.springframework.web.multipart.MultipartFile
//
//@RestController
//@RequestMapping("api/v1/minio")
//class MinioApi (private val uploadLogs: UploadLogs){
//
//    //post para subir a minio
//    @PostMapping("/upload")
//    fun uploadFile(@RequestParam("file") file: MultipartFile): String {
//        return uploadLogs.uploadFileMinio(file)
//    }
//
//}