package com.lms.app.service.impl;

import com.lms.app.config.properties.BlobProperties;
import com.lms.app.model.dto.commons.FileTypes;
import com.lms.app.model.dto.responses.BlobUploadResponse;
import com.lms.app.model.entities.Blob;
import com.lms.app.repository.BlobRepository;
import com.lms.app.service.BlobService;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.UUID;

@Service
public class BlobServiceImpl implements BlobService {
    private final Logger logger = LoggerFactory.getLogger(BlobServiceImpl.class);
    private final BlobProperties blobProperties;
    private final BlobRepository blobRepository;


    public BlobServiceImpl(BlobProperties blobProperties, BlobRepository blobRepository) {
        this.blobProperties = blobProperties;
        this.blobRepository = blobRepository;
    }
    @Override
    public BlobUploadResponse upload(MultipartFile file) throws IOException {
        logger.info("uploading file : {}", file.getOriginalFilename());
        var fileName = resolveFileName(file);
        if(!FileTypes.in(getFileExtension(fileName))){
            throw new RuntimeException("check supported Files");
        }
        String parentFolder = UUID.randomUUID().toString();
        Path path = Path.of(blobProperties.storagePath()).resolve(parentFolder);
        Files.createDirectories(path);

        path = path.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path);
        }
        var blob = new Blob();
        blob.setContentType(file.getContentType());
        blob.setDirectory(parentFolder);
        blob.setFileName(fileName);
        blob.setSizeInBytes(file.getSize());
        blob.setPath(path.toString());
        blob.setExtension(getFileExtension(fileName));
        var savedBlob = blobRepository.saveAndFlush(blob);
        return new BlobUploadResponse(savedBlob.getId());
    }

    @Override
    public byte[] download(Long blobId) throws IOException {
        try {
            var blob = blobRepository.getReferenceById(blobId);
            return Files.readAllBytes(Path.of(blob.getPath()));
        } catch (ObjectNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    private String resolveFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        return fileName == null || fileName.isBlank()
                ? "unknown.unknown"
                : Path.of(fileName).getFileName().toString();
    }

}
