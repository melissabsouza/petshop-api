package fiap.tds.petshop.controller;

import fiap.tds.petshop.dto.TutorDTO;
import fiap.tds.petshop.service.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private final TutorService tutorService;

    @GetMapping
    public String listarTutores(Model model) {
        List<TutorDTO> lista = tutorService.getAllTutors();
        model.addAttribute("tutores", lista);
        return "tutores/lista";
    }

    @GetMapping("/novo")
    public String novoTutor(Model model) {
        model.addAttribute("tutor", new TutorDTO());
        return "tutores/formulario";
    }

    @PostMapping
    public String salvarTutor(@Valid @ModelAttribute("tutor")
                                  TutorDTO tutor,
                              BindingResult bindingResults,
                              Model model) {
        if (bindingResults.hasErrors()){
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("tutor", tutor);
            model.addAttribute("isEdit", false);
            return "tutores/formulario";
        }
        tutorService.saveTutor(tutor);
        return "redirect:/tutores";
    }

    @GetMapping("/editar/{id}")
    public String editarTutor(@PathVariable Long id, Model model){
          model.addAttribute("tutor", tutorService.findById(id));
        model.addAttribute("isEdit", true);
          return "tutores/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String excluirTutor(@PathVariable Long id, Model model){
        tutorService.deleteById(id);
        return "redirect:/tutores";
    }

    @GetMapping("/meu-token")
    @ResponseBody
    public Map<String, Object> token(@AuthenticationPrincipal OidcUser user) {
        return user.getClaims();
    }




}
