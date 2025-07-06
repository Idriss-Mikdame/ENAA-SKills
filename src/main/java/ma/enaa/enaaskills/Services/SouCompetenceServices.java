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
    private final CompetenceServices competenceServices;

    public SouCompetenceServices(RepositoryCompetence repositoryCompetence, RepositorySousCompetence repositorySousCompetence, SousCompetenceMapper sousCompetenceMapper, CompetenceServices competenceServices) {
        this.repositoryCompetence = repositoryCompetence;
        this.repositorySousCompetence = repositorySousCompetence;
        this.sousCompetenceMapper = sousCompetenceMapper;
        this.competenceServices = competenceServices;
    }



    public SousCompetenceDto Ajouter(SousCompetenceDto dto){
        SousCompetence sousCompetence = sousCompetenceMapper.toEntity(dto);
        if (dto.getCompetenceId() != null){
            Competence competence = repositoryCompetence.findById(dto.getCompetenceId())
                    .orElseThrow(()-> new RuntimeException("Competence introuvable"));
            sousCompetence.setCompetence(competence);
        }

        checkAndUpdateCompetenceStatus(dto.getCompetenceId());

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
        sousCompetence.setEtatValidation(sousCompetenceDto.isEtatValidation());

        if (sousCompetenceDto.getCompetenceId() != null){
            Competence competence   = repositoryCompetence.findById(sousCompetenceDto.getCompetenceId())
                    .orElseThrow(() -> new RuntimeException("Competence introuvable"));
            sousCompetence.setCompetence(competence);

        }
        checkAndUpdateCompetenceStatus(sousCompetenceDto.getCompetenceId());

        return  sousCompetenceMapper.toDto(repositorySousCompetence.save(sousCompetence));
    }

    public void Supprimer(Long id){
        SousCompetence subCompetence = repositorySousCompetence.findById(id)
                .orElseThrow(() -> new RuntimeException("subCompetence not found"));
        Long competenceId = subCompetence.getCompetence().getId();
        repositorySousCompetence.deleteById(id);
        checkAndUpdateCompetenceStatus(competenceId);

    }

    public List<SousCompetenceDto> AfficherCompetenceByid(Long id){
        return repositorySousCompetence.findByCompetenceId(id)
                .stream().map(sousCompetenceMapper::toDto)
                .toList();
    }
//    public SousCompetenceDto updateValidation(Long id, boolean isValidated){
//        SousCompetence subCompetence = repositorySousCompetence.findById(id)
//                .orElseThrow(()->new RuntimeException("SubCompetence not found"));
//        subCompetence.setEtatValidation(isValidated);
//        SousCompetence saved = repositorySousCompetence.save(subCompetence);
//        return sousCompetenceMapper.toDto(saved);
//    }

    public boolean isAcquired(Long id){
        List<SousCompetence> sousCompetences =repositorySousCompetence.findByCompetenceId(id);
        return sousCompetences != null &&
                !sousCompetences.isEmpty() &&
                sousCompetences.stream().allMatch(SousCompetence::isEtatValidation);
    }

    private void  checkAndUpdateCompetenceStatus(Long id){
        boolean allvalide = isAcquired(id);
        Competence competence = repositoryCompetence.findById(id).orElseThrow(()->new RuntimeException(""));
        competence.setEtatValidation(allvalide);
        repositoryCompetence.save(competence);
    }
}


