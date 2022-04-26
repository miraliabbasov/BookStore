package com.ingressgroup.BookStore.model.map;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class BookMap {


    public static final BookMap INSTANCE = Mappers.getMapper(BookMap.class);

    public abstract BookDto entityToDto(BookEntity bookEntity);

    public abstract BookEntity dtoToEntity(BookDto bookDto);

    public abstract List<BookDto> entityToDtos(List<BookEntity> entities);


    public abstract BookEntity bookRequestToEntity(BookCreate bookCreate);


    public abstract List<BookCreate> entityToBookCreates(List<BookEntity> entities);
}
