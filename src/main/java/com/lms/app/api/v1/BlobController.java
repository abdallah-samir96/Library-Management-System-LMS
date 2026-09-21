package com.lms.app.api.v1;


import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.LMSResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(value = AppConstants.BLOB_API_V1_PATH)
public class BlobController {



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LMSResponse<UUID>> upload(@RequestPart("file")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println("Size In KB : "  + file.getSize() / 1024);
        var response = new LMSResponse<UUID>().setData(UUID.randomUUID()).build();
        return ResponseEntity.ok(response);
    }

}
