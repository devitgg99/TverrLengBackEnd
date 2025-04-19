package org.example.tverleng.model.mapper;

import org.example.tverleng.model.entities.AppUser;
import org.example.tverleng.model.response.AppUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserResponse toResponse(AppUser user);
}
