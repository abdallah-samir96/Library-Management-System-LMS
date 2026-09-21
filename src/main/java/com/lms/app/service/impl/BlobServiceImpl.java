package com.lms.app.service.impl;

import com.lms.app.service.BlobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlobServiceImpl implements BlobService {
    private final Logger logger = LoggerFactory.getLogger(BlobServiceImpl.class);
    @Override
    public String upload(MultipartFile file) {

        return "";
    }

    @Override
    public byte[] download(String path) {

        return new byte[0];
    }
}
