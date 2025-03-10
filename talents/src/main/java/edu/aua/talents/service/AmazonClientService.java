package edu.aua.talents.service;

import edu.aua.talents.persistance.entity.Talent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AmazonClientService {

    String uploadFile(MultipartFile file, Talent talent) throws IOException;

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    String uploadFile(MultipartFile file);

    String uploadFileMeta(MultipartFile file, Talent talent) throws IOException;

}
