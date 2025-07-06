package ma.enaa.enaaskills.Controller;

import ma.enaa.enaaskills.Dto.SousCompetenceDto;
import ma.enaa.enaaskills.Services.SouCompetenceServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SouCompetence")
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
    @GetMapping("/{id}")
    public SousCompetenceDto afficherParId(@PathVariable Long id) {
        return soucompetenceServices.afficherParid(id);
    }


    @PutMapping("/update/{id}")
    public SousCompetenceDto modifier(@PathVariable Long id, @RequestBody SousCompetenceDto sousCompetenceDto) {
        return soucompetenceServices.Modifier(id, sousCompetenceDto);
    }

    @DeleteMapping("{id}")
    public void Supprimer(@PathVariable Long id){
        soucompetenceServices.Supprimer(id);
    }


    @GetMapping("/competence/{competenceId}")
    public List<SousCompetenceDto> afficherParCompetence(@PathVariable Long competenceId) {
        return soucompetenceServices.AfficherCompetenceByid(competenceId);
    }
//    @PatchMapping("/{id}")
//    public SousCompetenceDto getCom(@PathVariable Long id, @RequestParam boolean isValid){
//        return soucompetenceServices.updateValidation(id,isValid);
//    }
}
