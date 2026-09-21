package com.lms.app.api.v1;


import com.lms.app.config.properties.BlobProperties;
import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.LMSResponse;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = AppConstants.BLOB_API_V1_PATH)
public class BlobController {

    private final BlobProperties blobProperties;

    public BlobController(BlobProperties blobProperties) {
        this.blobProperties = blobProperties;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LMSResponse<String>> upload(@RequestPart("file")MultipartFile file) throws IOException {
        String parentFolder = UUID.randomUUID().toString();
        Path path = Path.of(blobProperties.storagePath()).resolve(parentFolder);
        if(!path.toFile().exists()) { Files.createDirectories(path); }
        path = path.resolve(file.getOriginalFilename());
        Files.write(path, file.getBytes(), StandardOpenOption.CREATE_NEW);
        var response = new LMSResponse<String>().setData(parentFolder).build();
        return ResponseEntity.ok(response);
    }

}
