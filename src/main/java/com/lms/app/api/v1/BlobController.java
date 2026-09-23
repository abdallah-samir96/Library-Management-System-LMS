package com.lms.app.api.v1;


import com.lms.app.config.properties.BlobProperties;
import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.LMSResponse;
import com.lms.app.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = AppConstants.BLOB_API_V1_PATH)
public class BlobController {

    private final BlobService blobService;

    @Autowired
    public BlobController(BlobService blobService) {
        this.blobService = blobService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LMSResponse<Map<String, Object>>> upload(@RequestPart("file") MultipartFile file) throws IOException {
        var response = new LMSResponse<Map<String, Object>>()
                                .setData(blobService.upload(file))
                                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{filePath}/download")
    public ResponseEntity<byte[]> download(@PathVariable("filePath")  String path) throws IOException {
        var fileBytes = blobService.download(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"lms_file.pdf\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }
    @GetMapping("/{filePath}/read")
    public ResponseEntity<byte[]> view(@PathVariable("filePath")  String path) throws IOException {
        var fileBytes = blobService.download(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"lms_file.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(fileBytes);
    }
}
