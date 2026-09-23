package com.lms.app.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface BlobService {
    Map<String, Object> upload(MultipartFile file) throws IOException;
    byte[] download(String path) throws IOException;
}
