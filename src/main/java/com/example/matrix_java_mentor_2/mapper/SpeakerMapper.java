package com.example.matrix_java_mentor_2.mapper;

import com.example.matrix_java_mentor_2.entity.SpeakerEntity;
import com.example.matrix_java_mentor_2.model.SpeakerRequestDto;
import com.example.matrix_java_mentor_2.model.SpeakerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeakerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "talkEntities", ignore = true)
    SpeakerEntity toEntity(SpeakerRequestDto dto);
    SpeakerResponseDto toDto(SpeakerEntity speaker);
    List<SpeakerResponseDto> toDtoList(List<SpeakerEntity> speakers);
}
