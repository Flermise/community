package com.flermise.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.flermise.community.exception.CustomizeErrorCode;
import com.flermise.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class AliyunProvider {
    @Value("${Aliyun.afile.endPoint}")
    private String endpoint;

    @Value("${Aliyun.afile.AccessKeyId}")
    private String accessKeyId;

    @Value("${Aliyun.afile.AccessKeySecret}")
    private String accessKeySecret;

    @Value("${Aliyun.afile.BucketName}")
    private String bucketName;

    private OSS getConnect() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public String upload(InputStream inputStream, String fileName) {

        OSS ossClient = getConnect();
        try {
            String generatedFileName = "";
            String[] filePaths = fileName.split("\\.");
            if (filePaths.length > 1) {
                generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
            } else {
                return null;
            }
            ossClient.putObject(bucketName, generatedFileName, inputStream);
            Date date  = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365);
            URL url  = ossClient.generatePresignedUrl(bucketName,generatedFileName,date);
            ossClient.shutdown();
            return url.toString();
        }catch (OSSException e){
            throw new CustomizeException(CustomizeErrorCode.UPLOAD_FILE_ERROR);
        }
    }

}
