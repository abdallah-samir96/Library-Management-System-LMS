package com.lms.app.service.impl;

import com.lms.app.config.properties.BlobProperties;
import com.lms.app.service.BlobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.UUID;

@Service
public class BlobServiceImpl implements BlobService {
    private final BlobProperties blobProperties;
    private final Logger logger = LoggerFactory.getLogger(BlobServiceImpl.class);


    public BlobServiceImpl(BlobProperties blobProperties) {
        this.blobProperties = blobProperties;
    }
    @Override
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        logger.info("uploading file : {}", file.getOriginalFilename());
        // checking file type only
        // file size is validated on level of network
        String parentFolder = UUID.randomUUID().toString();
        Path path = Path.of(blobProperties.storagePath()).resolve(parentFolder);
        if(!path.toFile().exists()) { Files.createDirectories(path); }
        var fileName = file.getOriginalFilename() != null? file.getOriginalFilename(): "unknown";
        path = path.resolve(fileName);
        Files.write(path, file.getBytes(), StandardOpenOption.CREATE_NEW);
        return Map.of("FILE_PATH", parentFolder);
    }

    @Override
    public byte[] download(String path) throws IOException {

        var prentFolderPath = Path.of(blobProperties.storagePath()).resolve(path);
        try(var fileList = Files.list(prentFolderPath)){
            var fullFilePath = fileList.findFirst().orElseThrow(()-> new RuntimeException("No files inside the directory"));
            logger.info("Download File With Path: {}", fullFilePath);
            return Files.readAllBytes(fullFilePath);
        }

    }
}
