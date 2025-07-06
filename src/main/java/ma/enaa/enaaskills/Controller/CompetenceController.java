package ma.enaa.enaaskills.Controller;


import jakarta.servlet.http.HttpServletResponse;
import ma.enaa.enaaskills.Dto.CompetenceDto;
import ma.enaa.enaaskills.Model.Competence;
import ma.enaa.enaaskills.Services.CompetenceServices;
import ma.enaa.enaaskills.Services.ExceleServices;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Competence")
@CrossOrigin(origins = "*")
public class CompetenceController {
    private final ExceleServices exceleServices;
    private final CompetenceServices competenceServices;

    public CompetenceController(ExceleServices exceleServices, CompetenceServices competenceServices) {
        this.exceleServices = exceleServices;
        this.competenceServices = competenceServices;
    }

    @PostMapping
    public CompetenceDto AddCompetence(@RequestBody CompetenceDto competenceDto){
        return competenceServices.Ajouter(competenceDto);
    }

    @GetMapping
    public List<Competence> AfficherCompetence(){
        return competenceServices.ListCompetence();
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
    @GetMapping("/est-acquise/{id}")
    public boolean estAcquise(@PathVariable Long id) {
        return competenceServices.estCompetenceAcquise(id);
    }
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // 1. Définir le type de contenu de la réponse
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 2. Créer un nom de fichier dynamique
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rapportcompetences" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // 3. Récupérer les données à exporter
        List<Competence> competences = competenceServices.ListCompetence();

        // 4. Appeler le service d'export
        exceleServices.exportCompetencesToExcel(competences, response.getOutputStream());
    }
}
