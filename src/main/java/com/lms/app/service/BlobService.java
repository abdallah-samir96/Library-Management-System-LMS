package com.lms.app.service;


import org.springframework.web.multipart.MultipartFile;

public interface BlobService {
    String upload(MultipartFile file);
    byte[] download(String path);
}
