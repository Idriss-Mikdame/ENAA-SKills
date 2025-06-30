package ma.enaa.enaaskills.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity

public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    private String description;
    @ManyToOne
    private  Apprenant apprenant;
    @OneToMany(mappedBy = "competence")
    private List<SousCompetence> sousCompetence;

}
