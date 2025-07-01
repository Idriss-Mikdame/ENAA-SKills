package ma.enaa.enaaskills.Mapper;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Model.SousCompetence;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring", uses = SousCompetenceMapper.class)

public interface SousCompetenceMapper {

    SousCompetence toEntity(SousCompetenceDto sousCompetenceDto);
    SousCompetenceDto toDto(SousCompetence sousCompetence);
}