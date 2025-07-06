package ma.enaa.enaaskills.Services;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Mapper.SousCompetenceMapper;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Model.SousCompetence;
import ma.enaa.enaaskills.Repository.RepositoryCompetence;
import ma.enaa.enaaskills.Repository.RepositorySousCompetence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SouCompetenceServicesTest {

//    @Mock
    private RepositoryCompetence repositoryCompetence;

//    @Mock
    private RepositorySousCompetence repositorySousCompetence;

//    @Mock
    private SousCompetenceMapper sousCompetenceMapper;

//    @InjectMocks
    private SouCompetenceServices service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ajouter() {
        SousCompetenceDto dto = new SousCompetenceDto();
        dto.setTitre("Test");
        dto.setDescription("Description test");
        dto.setCompetenceId(1L);

        SousCompetence entity = new SousCompetence();
        Competence competence = new Competence();
        competence.setId(1L);
        entity.setCompetence(competence);

        when(sousCompetenceMapper.toEntity(dto)).thenReturn(entity);
        when(repositoryCompetence.findById(1L)).thenReturn(Optional.of(competence));
        when(repositorySousCompetence.save(entity)).thenReturn(entity);
        when(sousCompetenceMapper.toDto(entity)).thenReturn(dto);

        SousCompetenceDto result = service.Ajouter(dto);

        assertEquals(dto.getTitre(), result.getTitre());
        verify(repositorySousCompetence).save(entity);
    }

    @Test
    void afficherParid() {
        SousCompetence entity = new SousCompetence();
        entity.setId(2L);

        SousCompetenceDto dto = new SousCompetenceDto();
        dto.setId(2L);

        when(repositorySousCompetence.findById(2L)).thenReturn(Optional.of(entity));
        when(sousCompetenceMapper.toDto(entity)).thenReturn(dto);

        SousCompetenceDto result = service.afficherParid(2L);

        assertEquals(2L, result.getId());
    }

    @Test
    void modifier() {
        SousCompetenceDto dto = new SousCompetenceDto();
        dto.setTitre("Updated");
        dto.setDescription("Updated Desc");
        dto.setCompetenceId(1L);

        SousCompetence existing = new SousCompetence();
        existing.setId(1L);
        Competence competence = new Competence();
        competence.setId(1L);

        when(repositorySousCompetence.findById(1L)).thenReturn(Optional.of(existing));
        when(repositoryCompetence.findById(1L)).thenReturn(Optional.of(competence));
        when(repositorySousCompetence.save(existing)).thenReturn(existing);
        when(sousCompetenceMapper.toDto(existing)).thenReturn(dto);

        SousCompetenceDto result = service.Modifier(1L, dto);

        assertEquals("Updated", result.getTitre());
    }

    @Test
    void afficherCompetenceByid() {
        SousCompetence sc = new SousCompetence();
        sc.setId(1L);
        SousCompetenceDto dto = new SousCompetenceDto();
        dto.setId(1L);

        when(repositorySousCompetence.findByCompetenceId(1L)).thenReturn(List.of(sc));
        when(sousCompetenceMapper.toDto(sc)).thenReturn(dto);

        List<SousCompetenceDto> result = service.AfficherCompetenceByid(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
}
