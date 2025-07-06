package ma.enaa.enaaskills.Services;

import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Mapper.CompetenceMapper;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Model.SousCompetence;
import ma.enaa.enaaskills.Repository.RepositoryCompetence;
import ma.enaa.enaaskills.Repository.RepositorySousCompetence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceServices {
    private final RepositoryCompetence repositoryCompetence;
    private final CompetenceMapper competenceMapper;
    private final RepositorySousCompetence repositorySousCompetence;

    public CompetenceServices(RepositoryCompetence repositoryCompetence, CompetenceMapper competenceMapper, RepositorySousCompetence repositorySousCompetence) {
        this.repositoryCompetence = repositoryCompetence;
        this.competenceMapper = competenceMapper;
        this.repositorySousCompetence = repositorySousCompetence;
    }

    public CompetenceDto Ajouter(CompetenceDto competenceDto){
        Competence competence =  competenceMapper.toEntity(competenceDto);
        return competenceMapper.toDto(repositoryCompetence.save(competence));
    }

    public List<Competence> ListCompetence() {
        return repositoryCompetence.findAll();
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

    public boolean estCompetenceAcquise(Long competenceId) {
        List<SousCompetence> sousCompetences = repositorySousCompetence.findByCompetenceId(competenceId);

        if (sousCompetences.isEmpty()) return false;

        long valides = sousCompetences.stream().filter(SousCompetence::isEtatValidation).count();
        return valides == sousCompetences.size(); // 100% validées
    }


}
