package ma.enaa.enaaskills.Repository;

import ma.enaa.enaaskills.Model.SousCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorySousCompetence extends JpaRepository<SousCompetence,Long> {
    List<SousCompetence> findByCompetenceId(Long id);

}
