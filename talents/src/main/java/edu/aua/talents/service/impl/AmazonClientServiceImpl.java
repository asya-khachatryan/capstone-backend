package edu.aua.talents.service.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AmazonClientServiceImpl implements AmazonClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonClientServiceImpl.class);

    private AmazonS3 amazonS3;

    @Value("${amazon.s3.endpoint}")
    private String url;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.access-key}")
    private String accessKey;

    @Value("${amazon.s3.secret-key}")
    private String secretKey;

    protected AmazonS3 getClient() {
        return amazonS3;
    }

    protected String getUrl() {
        return url;
    }

    protected String getBucketName() {
        return bucketName;
    }

    @PostConstruct
    private void init() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(MultipartFile file, Talent talent) {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
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
        LOGGER.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return fileName;
    }


    public byte[] downloadFile(String fileName) {
        LOGGER.info("Requested to download a file from AWS S3 - {}", fileName);
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            LOGGER.info("IN downloadFile AWS : {} file successfully downloaded", fileName);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        LOGGER.info("Requested to delete a file from AWS S3 - {}", fileName);
        amazonS3.deleteObject(bucketName, fileName);
        LOGGER.info("IN deleteFile AWS : {} file successfully deleted", fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        LOGGER.info("Requested to covert a MultipartFile to a File object - {}", file.getName());
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("IN convertMultiPartFileToFile AWS : {} file successfully converted", file.getName());
        return convertedFile;
    }

    public String uploadFile(MultipartFile file) {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        LOGGER.info("In uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }

    public String uploadFileMeta(MultipartFile file, Talent talent) throws IOException {
        LOGGER.info("Requested to upload a file to AWS S3 - {}", file.getName());
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
        LOGGER.info("IN uploadFile AWS : {} file successfully uploaded", fileName);
        return "File uploaded : " + fileName;
    }
}





