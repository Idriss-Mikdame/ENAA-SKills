package ma.enaa.enaaskills.Services;

import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Mapper.CompetenceMapper;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Repository.RepositoryCompetence;
import ma.enaa.enaaskills.Repository.RepositorySousCompetence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceServices {
    private final RepositoryCompetence repositoryCompetence;
    private final CompetenceMapper competenceMapper;

    public CompetenceServices(RepositoryCompetence repositoryCompetence, CompetenceMapper competenceMapper) {
        this.repositoryCompetence = repositoryCompetence;
        this.competenceMapper = competenceMapper;
    }

    public CompetenceDto Ajouter(CompetenceDto competenceDto){
        Competence competence =  competenceMapper.toEntity(competenceDto);
        return competenceMapper.toDto(repositoryCompetence.save(competence));
    }

    public List<CompetenceDto> List(){
        return repositoryCompetence.findAll().stream()
                .map(competenceMapper::toDto).toList();
    }

    public CompetenceDto AfficherParId(Long id){
        Competence competence =repositoryCompetence.findById(id).
                orElseThrow(()-> new RuntimeException("Competence non trouvé"));
        return competenceMapper.toDto(competence);
    }

    public CompetenceDto Modifier(Long id, CompetenceDto competenceDto){
        Competence competence = repositoryCompetence.findById(id).orElseThrow(()-> new RuntimeException("Competence non trouvé"));

        competence.setNom(competenceDto.getNom());
        competence.setCode(competenceDto.getCode());
        competence.setDescription(competenceDto.getDescription());
        return competenceMapper.toDto(repositoryCompetence.save(competence));

    }

    public void Supprimer(Long id){
        repositoryCompetence.deleteById(id);
    }

}
