package com.lms.app.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.blobs")
public record BlobProperties(
        String storagePath
) {
}