package fiap.tds.petshop.controller;

import fiap.tds.petshop.dto.TutorDTO;
import fiap.tds.petshop.service.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/editar/{cpf}")
    public String editarTutor(@PathVariable String cpf, Model model){
          model.addAttribute("tutor", tutorService.findById(cpf));
        model.addAttribute("isEdit", true);
          return "tutores/formulario";
    }

    @GetMapping("/deletar/{cpf}")
    public String excluirTutor(@PathVariable String cpf, Model model){
        tutorService.deleteById(cpf);
        return "redirect:/tutores";
    }


}
