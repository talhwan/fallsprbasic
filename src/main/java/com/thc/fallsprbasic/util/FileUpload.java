package com.thc.fallsprbasic.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUpload {
    public static String upload(MultipartFile file){
        String returnValue = null;
        try{
            if(file == null || file.isEmpty()){
                return null;
            }
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
            returnValue = temp_date + "_" + filename;

            FileCopyUtils.copy(file.getBytes(), new File(filePath + returnValue));
        } catch (Exception e){
        }
        return returnValue;
    }
}
