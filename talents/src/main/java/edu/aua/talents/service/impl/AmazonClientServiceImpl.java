package edu.aua.talents.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import edu.aua.talents.persistance.entity.Specialization;
import edu.aua.talents.persistance.entity.Talent;
import edu.aua.talents.service.AmazonClientService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@Log4j2
@PropertySource("classpath:talents.properties")
public class AmazonClientServiceImpl implements AmazonClientService {

    private AmazonS3 amazonS3;

    @Value("${amazon.s3.endpoint}")
    private String url;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.access-key}")
    private String accessKey;

    @Value("${amazon.s3.secret-key}")
    private String secretKey;

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl(bucketName, fileName, calendar.getTime(), httpMethod).toString();
    }

    public String getUrlByFileName(String fileName) {
        if (!amazonS3.doesObjectExist(bucketName, fileName))
            return "File does not exist";
        log.info("Generating signed URL for file name {}", fileName);
        return generateUrl(fileName, HttpMethod.GET);
    }

    @PostConstruct
    private void init() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_3)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(MultipartFile file, Talent talent) {
        System.out.println(bucketName);
        log.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        final Specialization specialization = talent.getSpecialization();
        final String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        final String fileName = new StringBuilder()
                .append(specialization.getSpecializationType())
                .append("-").append(yearMonth)
                .append("-").append(talent.getFullName())
                .append("-").append("CV").toString();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        log.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return fileName;
    }


    public byte[] downloadFile(String fileName) {
        log.info("Requested to download a file from AWS S3 - {}", fileName);
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            log.info("IN downloadFile AWS : {} file successfully downloaded", fileName);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        log.info("Requested to delete a file from AWS S3 - {}", fileName);
        amazonS3.deleteObject(bucketName, fileName);
        log.info("IN deleteFile AWS : {} file successfully deleted", fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        log.info("Requested to covert a MultipartFile to a File object - {}", file.getName());
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("IN convertMultiPartFileToFile AWS : {} file successfully converted", file.getName());
        return convertedFile;
    }

    public String uploadFile(MultipartFile file) {
        log.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        log.info("In uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }

    public String uploadFileMeta(MultipartFile file, Talent talent) throws IOException {
        log.info("Requested to upload a file to AWS S3 - {}", file.getName());
        final Specialization specialization = talent.getSpecialization();
        final String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        final String fileName = new StringBuilder()
                .append(specialization.getSpecializationType())
                .append("/").append(yearMonth)
                .append("_").append(talent.getFullName())
                .append("_").append(file.getOriginalFilename()).toString();
        InputStream inputStream = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(inputStream.available());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata));
        log.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }
}





