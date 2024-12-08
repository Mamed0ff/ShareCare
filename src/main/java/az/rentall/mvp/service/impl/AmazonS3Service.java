package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.FileUploadException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;


    public String uploadFile(MultipartFile file){
        File fileObj = convertMultipartFileToFile(file);

//        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
           String fileName=System.currentTimeMillis()+"_"+UUID.randomUUID();
        amazonS3.putObject(new PutObjectRequest(bucketName,fileName,fileObj)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        boolean delete = fileObj.delete();
        return amazonS3.getUrl(bucketName,fileName).toString();
    }

    public File convertMultipartFileToFile (MultipartFile file){
    File convertedFile =new File(Objects.requireNonNull(file.getOriginalFilename()));

    try(FileOutputStream fos = new FileOutputStream(convertedFile)){
        fos.write(file.getBytes());
    }catch (IOException e){
        throw new FileUploadException(e.getMessage());
    }
    return convertedFile;
    }


}
