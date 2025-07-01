package ma.enaa.enaaskills.Controller;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Services.SouCompetenceServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SouCompetence")
@CrossOrigin(origins = "*")
public class SousCompetenceController {
    private final SouCompetenceServices soucompetenceServices;


    public SousCompetenceController(SouCompetenceServices competenceServices) {
        this.soucompetenceServices = competenceServices;
    }
    @PostMapping
    public SousCompetenceDto Ajoute(@RequestBody SousCompetenceDto sousCompetenceDto){
   return   soucompetenceServices.Ajouter(sousCompetenceDto);
    }

    @GetMapping
    public List<SousCompetenceDto> Afficher(){
        return soucompetenceServices.Afficher();
    }
    @GetMapping("{id}")
    public SousCompetenceDto afficherParid(@PathVariable Long id){
        return soucompetenceServices.afficherParid(id);
    }

    @PutMapping("/updat/{id}")
    public SousCompetenceDto Modifier(@PathVariable Long id , @PathVariable SousCompetenceDto sousCompetenceDto){
        return soucompetenceServices.Modifier(id,sousCompetenceDto);
    }

    @DeleteMapping("{id}")
    public void Supprimer(@PathVariable Long id){
        soucompetenceServices.Supprimer(id);
    }

    @GetMapping("{CompetenceId}")
    public List<SousCompetenceDto> AfficherCompetenceByid(@PathVariable Long CompetenceId){
        return soucompetenceServices.AfficherCompetenceByid(CompetenceId);
    }
}
