package ma.enaa.enaaskills.Services;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Mapper.SousCompetenceMapper;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Model.SousCompetence;
import ma.enaa.enaaskills.Repository.RepositoryCompetence;
import ma.enaa.enaaskills.Repository.RepositorySousCompetence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SouCompetenceServices {
    private final RepositoryCompetence repositoryCompetence;
    private final RepositorySousCompetence repositorySousCompetence;
    private final SousCompetenceMapper sousCompetenceMapper;

    public SouCompetenceServices(RepositoryCompetence repositoryCompetence, RepositorySousCompetence repositorySousCompetence, SousCompetenceMapper sousCompetenceMapper) {
        this.repositoryCompetence = repositoryCompetence;
        this.repositorySousCompetence = repositorySousCompetence;
        this.sousCompetenceMapper = sousCompetenceMapper;
    }

    public SousCompetenceDto Ajouter(SousCompetenceDto dto){
        SousCompetence sousCompetence = sousCompetenceMapper.toEntity(dto);
        if (dto.getCompetenceId() != null){
            Competence competence = repositoryCompetence.findById(dto.getCompetenceId())
                    .orElseThrow(()-> new RuntimeException("Competence introuvable"));
            sousCompetence.setCompetence(competence);
        }
        return sousCompetenceMapper.toDto(repositorySousCompetence.save(sousCompetence));
    }


    public List<SousCompetenceDto> Afficher(){
        return repositorySousCompetence.findAll().stream()
                .map(sousCompetenceMapper::toDto)
                .toList();
    }

    public SousCompetenceDto afficherParid(Long id){
        SousCompetence sousCompetence = repositorySousCompetence.findById(id)
                .orElseThrow(()-> new RuntimeException("SousCompétence non trouvée"));
        return sousCompetenceMapper.toDto(sousCompetence);
    }

    public SousCompetenceDto Modifier(Long id , SousCompetenceDto sousCompetenceDto){
        SousCompetence sousCompetence = repositorySousCompetence.findById(id)
                .orElseThrow(()-> new RuntimeException("SousCompétence non trouvée"));

        sousCompetence.setTitre(sousCompetenceDto.getTitre());
        sousCompetence.setDescription(sousCompetenceDto.getDescription());

        if (sousCompetenceDto.getCompetenceId() != null){
            Competence competence   = repositoryCompetence.findById(sousCompetenceDto.getCompetenceId())
                    .orElseThrow(() -> new RuntimeException("Competence introuvable"));
            sousCompetence.setCompetence(competence);

        }
        return  sousCompetenceMapper.toDto(repositorySousCompetence.save(sousCompetence));
    }

    public void Supprimer(Long id){
        repositorySousCompetence.deleteById(id);
    }

    public List<SousCompetenceDto> AfficherCompetenceByid(Long id){
        return repositorySousCompetence.findByCompetenceId(id)
                .stream().map(sousCompetenceMapper::toDto)
                .toList();
    }
}
