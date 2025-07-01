package ma.enaa.enaaskills.Repository;

import ma.enaa.enaaskills.Model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepositoryCompetence extends JpaRepository<Competence,Long> {
}
