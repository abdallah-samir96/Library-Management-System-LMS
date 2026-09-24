package com.lms.app.service;


import com.lms.app.model.dto.responses.BlobUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BlobService {
    BlobUploadResponse upload(MultipartFile file) throws IOException;
    byte[] download(Long blobId) throws IOException;
}
