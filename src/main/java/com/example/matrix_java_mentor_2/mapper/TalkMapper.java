package com.example.matrix_java_mentor_2.mapper;

import com.example.matrix_java_mentor_2.entity.TalkEntity;
import com.example.matrix_java_mentor_2.model.TalkRequestDto;
import com.example.matrix_java_mentor_2.model.TalkResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TalkMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "speaker", ignore = true)
    TalkEntity toEntity(TalkRequestDto dto);

    @Mapping(source = "speaker.id", target = "speakerId")
    @Mapping(source = "speaker.fullName", target = "speakerName")
    TalkResponseDto toDto(TalkEntity talk);

    List<TalkResponseDto> toDtoList(List<TalkEntity> talks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "speaker", ignore = true)
    void updateEntity(TalkRequestDto dto, @MappingTarget TalkEntity talk);

}
