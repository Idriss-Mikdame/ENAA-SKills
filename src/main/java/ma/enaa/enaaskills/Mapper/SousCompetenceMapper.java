package ma.enaa.enaaskills.Mapper;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Model.SousCompetence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SousCompetenceMapper.class)

public interface SousCompetenceMapper {

    @Mapping(source = "competenceId", target = "competence.id")
    SousCompetence toEntity(SousCompetenceDto sousCompetenceDto);

    @Mapping(target = "competenceId", source = "competence.id")
    SousCompetenceDto toDto(SousCompetence sousCompetence);
}