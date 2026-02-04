package com.recettes.Recettes.mappers;

import org.mapstruct.Mapper;

import com.recettes.Recettes.dto.UserRequestDto;
import com.recettes.Recettes.dto.UserResponse;
import com.recettes.Recettes.entities.User;



@Mapper(componentModel = "spring")
public interface UserMapper {
UserRequestDto toDto(User user);
User toEntity(UserRequestDto userRequestDto);
UserResponse toResponse(User user);
User toEntity(UserResponse userResponse);
    
}
