package ma.enaa.enaaskills.Services;

import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Mapper.CompetenceMapper;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Repository.RepositoryCompetence;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CompetenceServicesTest {
//    @Mock
    private CompetenceMapper competenceMap;
//    @Mock
    private RepositoryCompetence competenceRepository;
//    @InjectMocks
    private CompetenceServices competenceService;
    @Test
    void ajouterCompetence() {
        CompetenceDto competenceDto = new CompetenceDto();
        competenceDto.setNom("getion stress");
        competenceDto.setCode("C7");
        competenceDto.setDescription("Savoir s’ajuster à différents environnements, tâches ou interlocuteurs");
        Competence competence = new Competence();
        competence.setNom("getion stress");
        competence.setCode("C7");
        competence.setDescription("Savoir s’ajuster à différents environnements, tâches ou interlocuteurs");

    }

    @Test
    void list() {

    }

    @Test
    void afficherParId() {
    }

    @Test
    void modifier() {
          }

    @Test
    void supprimer() {
    }
}