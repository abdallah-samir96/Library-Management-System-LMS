package com.lms.app.service.impl;

import com.lms.app.config.properties.BlobProperties;
import com.lms.app.model.dto.commons.FileTypes;
import com.lms.app.model.dto.responses.BlobUploadResponse;
import com.lms.app.model.entities.Blob;
import com.lms.app.repository.BlobRepository;
import com.lms.app.service.BlobService;
import com.lms.app.utils.PDFUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class BlobServiceImpl implements BlobService {
    private static final String THUMBNAIL_JPG = "thumbnail.jpg";
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
        Path parentFolderPath = Path.of(blobProperties.storagePath()).resolve(parentFolder);
        Files.createDirectories(parentFolderPath);

        var pdfFilePath = parentFolderPath.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, pdfFilePath);
        }
        var thumbnailPath = generateThumbnail(pdfFilePath, parentFolderPath);
        var blob = new Blob();
        blob.setContentType(file.getContentType());
        blob.setDirectory(parentFolder);
        blob.setFileName(fileName);
        blob.setSizeInBytes(file.getSize());
        blob.setPath(pdfFilePath.toString());
        blob.setThumbnailPath(thumbnailPath);
        blob.setExtension(getFileExtension(fileName));
        var savedBlob = blobRepository.saveAndFlush(blob);
        return new BlobUploadResponse(savedBlob.getId());
    }

    @Override
    public byte[] download(Long blobId) throws IOException {
        var blobOptional = blobRepository.findById(blobId);
        if(blobOptional.isPresent()) {
            return Files.readAllBytes(Path.of(blobOptional.get().getPath()));
        }
        throw new RuntimeException("file is not exists");
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

    /** passing path of the file to avoid reading all bytes of pdf file to generate the thumbnail */
    private String generateThumbnail(Path pdfPath, Path parentFolder) throws IOException {
        var filePath = parentFolder.resolve(THUMBNAIL_JPG);
        Files.copy(new ByteArrayInputStream(PDFUtils.generateThumbnail(pdfPath)), filePath);
        return filePath.toString();
    }
}