package com.lms.app.model.dto.commons;

import java.util.Arrays;

public enum FileTypes {
    PDF("pdf");

    public final String type;

    FileTypes(String type) {
        this.type = type;
    }
    public static boolean in(String fileType) {
        return Arrays.stream(FileTypes.values())
                .anyMatch(it -> (it.type.equalsIgnoreCase(fileType)));
    }
}
