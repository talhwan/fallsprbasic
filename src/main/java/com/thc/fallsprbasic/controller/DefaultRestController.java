package com.thc.fallsprbasic.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("/api")
@RestController
public class DefaultRestController {
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        //위까지는 파일을 프론트에서 잘 전달해줌!!

        //이제 가져온 파일을 어딘가에 저장해줘야 함!
        //"Users/workspace/~~~"; //맥에서는 이런 식으로 하는 것 같음
        String filePath = "C:/workspace/uploadfiles/fallsprbasic/";
        File newfile = new File(filePath);
        // File 객체에 담긴 폴더가 존재하는지 물어봄!
        if(!newfile.exists()) {
            // File 객체에 담긴 폴더가 존재안하면 강제 생성!!
            newfile.mkdirs();
        }

        //파일명 중복을 막기 위해 현재 시각 가져오기!
        Date date = new Date();
        String temp_date = date.getTime() + "";
        String finalName = filePath + temp_date + "_" + filename;
        FileCopyUtils.copy(file.getBytes(), new File(finalName));

        return ResponseEntity.status(HttpStatus.OK).body(temp_date + "_" + filename);
    }
}
