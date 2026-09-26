package com.lms.app.model.dto.commons;

import java.util.Arrays;

public enum SortDirection {
    DESC("desc"),
    ASC("asc");

    public final String direction;
    SortDirection(String dir) {
        this.direction = dir;
    }
    public static SortDirection getDirection(String val) {
        return Arrays
                .stream(SortDirection.values())
                .filter(it -> (it.direction.equalsIgnoreCase(val)))
                .findFirst().orElseThrow(()-> new RuntimeException("Direction not found"));
    }
}
