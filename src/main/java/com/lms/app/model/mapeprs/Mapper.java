package com.lms.app.model.mapeprs;

import java.util.List;

public interface Mapper <E, D>{
    E toEntity(D dto);
    D toDTO(E entity);

    default void mapToDTO(E e, D d){}
    default List<E> toEntities(List<D> dtoList){ return dtoList.stream().map(this::toEntity).toList();}
    default List<D> toDTOs(List<E> entities){ return entities.stream().map(this::toDTO).toList();}
}
