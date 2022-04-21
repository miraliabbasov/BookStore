package com.ingressgroup.BookStore.model.map;

import com.ingressgroup.BookStore.dao.entity.PublisherEntity;
import com.ingressgroup.BookStore.model.dto.PublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PublisherMap {

    public static final PublisherMap INSTANCE = Mappers.getMapper(PublisherMap.class);

    public abstract PublisherDto entityToDto(PublisherEntity publisherEntity);

    public abstract PublisherEntity dtoToEntity(PublisherDto publisherDto);

}
