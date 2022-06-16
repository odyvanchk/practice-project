package com.practice.shop.services.mappers;


public interface Mapper<T, D> {
    T toEntity(D dtoEntity);

    D toDTO(T entity);
}
