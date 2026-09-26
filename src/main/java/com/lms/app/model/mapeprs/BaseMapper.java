package com.lms.app.model.mapeprs;

import com.lms.app.model.dto.requests.BaseEntityResponse;
import com.lms.app.model.entities.BaseEntity;

public class BaseMapper  {

    public static void mapToDTO(BaseEntity baseEntity, BaseEntityResponse baseEntityResponse) {
        baseEntityResponse.setCreatedAt(baseEntity.getCreatedAt());
        baseEntityResponse.setCreatedBy(baseEntity.getCreatedBy());
        baseEntityResponse.setUpdatedAt(baseEntity.getUpdatedAt());
        baseEntityResponse.setUpdatedBy(baseEntity.getUpdatedBy());
        baseEntityResponse.setDeletedAt(baseEntity.getDeletedAt());
    }
}