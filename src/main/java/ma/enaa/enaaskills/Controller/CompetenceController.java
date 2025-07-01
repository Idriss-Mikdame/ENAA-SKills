package ma.enaa.enaaskills.Controller;


import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Services.CompetenceServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Competence")
@CrossOrigin(origins = "*")
public class CompetenceController {

    private final CompetenceServices competenceServices;

    public CompetenceController(CompetenceServices competenceServices) {
        this.competenceServices = competenceServices;
    }

    @PostMapping
    public CompetenceDto AddCompetence(@RequestBody CompetenceDto competenceDto){
        return competenceServices.Ajouter(competenceDto);
    }

    @GetMapping
    public List<CompetenceDto> AfficherCompetence(){
        return competenceServices.List();
    }

    @GetMapping("{id}")
    public CompetenceDto AfficherParid(@PathVariable Long id){
        return competenceServices.AfficherParId(id);
    }

    @PutMapping("{id}")
    public CompetenceDto ModifierCompetence(@PathVariable Long id , @RequestBody CompetenceDto competenceDto){
        return competenceServices.Modifier(id,competenceDto);
    }

    @DeleteMapping("{id}")
    public  void SupprimerCompetence(@PathVariable Long id){
        competenceServices.Supprimer(id);
    }

}
