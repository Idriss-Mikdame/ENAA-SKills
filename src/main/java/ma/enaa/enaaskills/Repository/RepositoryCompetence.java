package ma.enaa.enaaskills.Repository;

import ma.enaa.enaaskills.Model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RepositoryCompetence extends JpaRepository<Competence,Long> {
    @Query("SELECT c FROM Competence c LEFT JOIN FETCH c.sousCompetence WHERE c.id = :id")
    Optional<Competence> findByIdWithSousCompetences(@Param("id") Long id);
}
