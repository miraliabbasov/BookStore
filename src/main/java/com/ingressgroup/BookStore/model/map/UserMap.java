package com.ingressgroup.BookStore.model.map;

import com.ingressgroup.BookStore.dao.entity.UserEntity;
import com.ingressgroup.BookStore.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract  class UserMap {

    public static final UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    public abstract UserDto entityToDto(UserEntity entity);

    public abstract UserEntity dtoToEntity(UserDto userDto);

}
