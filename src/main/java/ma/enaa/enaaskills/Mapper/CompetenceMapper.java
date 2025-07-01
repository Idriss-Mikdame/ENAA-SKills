package ma.enaa.enaaskills.Mapper;

import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Model.Competence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {
    Competence toEntity(CompetenceDto competenceDto);
    CompetenceDto toDto(Competence competence);

}
