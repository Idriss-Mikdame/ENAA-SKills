package ma.enaa.enaaskills.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean etatValidation;
    @JsonIgnore
    @OneToMany(mappedBy = "competence")
    private List<SousCompetence> sousCompetence;


//    public boolean SimpleIsAcquired(){
//        if(sousCompetence  == null || sousCompetence .isEmpty()){
//            return false;
//        }
//        for (SousCompetence  sc: sousCompetence ){
//            if (!sc.isEtatValidation()){
//                return false;
//            }
//        }
//        return true;
//    }
    public Competence() {
    }

    public boolean isEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(boolean etatValidation) {
        this.etatValidation = etatValidation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<SousCompetence> getSousCompetence() {
        return sousCompetence;
    }

    public void setSousCompetence(List<SousCompetence> sousCompetence) {
        this.sousCompetence = sousCompetence;
    }
}
