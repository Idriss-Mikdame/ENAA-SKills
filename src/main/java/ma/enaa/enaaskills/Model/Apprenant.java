package ma.enaa.enaaskills.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    @OneToMany(mappedBy = "apprenant")
    private List<Competence> competence;
}
