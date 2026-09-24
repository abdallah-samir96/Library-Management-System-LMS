package com.lms.app.api.v1;


import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.responses.BlobUploadResponse;
import com.lms.app.model.dto.responses.LMSResponse;
import com.lms.app.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = AppConstants.BLOB_API_V1_PATH)
public class BlobController {

    private final BlobService blobService;

    @Autowired
    public BlobController(BlobService blobService) {
        this.blobService = blobService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LMSResponse<BlobUploadResponse>> upload(@RequestPart("file") MultipartFile file) throws IOException {
        var response = new LMSResponse<BlobUploadResponse>()
                                .setData(blobService.upload(file))
                                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{blobId}/download")
    public ResponseEntity<byte[]> download(@PathVariable("blobId")  Long blobId) throws IOException {
        var fileBytes = blobService.download(blobId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"lms_file.pdf\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }
    @GetMapping("/{blobId}/read")
    public ResponseEntity<byte[]> view(@PathVariable("blobId")  Long blobId) throws IOException {
        var fileBytes = blobService.download(blobId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"lms_file.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(fileBytes);
    }
}
