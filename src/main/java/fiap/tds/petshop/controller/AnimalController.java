package fiap.tds.petshop.controller;

import fiap.tds.petshop.dto.AnimalDTO;
import fiap.tds.petshop.dto.TutorDTO;
import fiap.tds.petshop.service.AnimalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/animais")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public String listarAnimais(Model model) {
        List<AnimalDTO> lista = animalService.getAllAnimals();
        model.addAttribute("animais", lista);
        return "animais/lista";
    }

    @GetMapping("/novo")
    public String novoAnimal(Model model) {
        model.addAttribute("animal", new AnimalDTO());
        return "animais/formulario";
    }

    @PostMapping
    public String salvarAnimal(@Valid @ModelAttribute("animal")
                              AnimalDTO animal,
                              BindingResult bindingResults,
                              Model model) {
        if (bindingResults.hasErrors()){
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("animal", animal);
            model.addAttribute("isEdit", false);
            return "animais/formulario";
        }
        animalService.saveAnimal(animal);
        return "redirect:/animais";
    }

    @GetMapping("/editar/{id}")
    public String editarAnimal(@PathVariable Long id, Model model){
        model.addAttribute("animal", animalService.findById(id));
        model.addAttribute("isEdit", true);
        return "animais/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String excluirTutor(@PathVariable Long id, Model model){
        animalService.deleteById(id);
        return "redirect:/animais";
    }
}
