package ma.enaa.enaaskills.Model;

import jakarta.persistence.*;

@Entity

public class SousCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    @ManyToOne
    private Competence competence;
}
