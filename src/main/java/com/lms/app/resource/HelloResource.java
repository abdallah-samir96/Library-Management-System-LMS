package com.lms.app.resource;

import com.lms.app.model.dto.LMSResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/v1/welcome")
public class HelloResource {

    @GetMapping
    public ResponseEntity<LMSResponse<List<String>>> welcome() {
        var response = new LMSResponse<List<String>>()
                .setData(List.of("Ahmed", "Mohamed"))
                .setTotalCounts(100)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
