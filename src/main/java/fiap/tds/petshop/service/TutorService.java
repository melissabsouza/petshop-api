package fiap.tds.petshop.service;

import fiap.tds.petshop.dto.TutorDTO;
import fiap.tds.petshop.entity.Animal;
import fiap.tds.petshop.entity.Tutor;
import fiap.tds.petshop.repository.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TutorService {

    @Autowired
    private final TutorRepository tutorRepository;

    public TutorDTO saveTutor(TutorDTO tutorDTO) {
        Tutor tutor = toEntity(tutorDTO);

        if (tutorDTO.getId() == null ) {
            tutor = tutorRepository.save(tutor);
        } else {
            TutorDTO byId = this.findById(tutorDTO.getId());
            byId.setCpf(tutorDTO.getCpf());
            byId.setEmail(tutorDTO.getEmail());
            byId.setNome(tutorDTO.getNome());
            byId.setTelefone(tutor.getTelefone());

            tutor = tutorRepository.save(toEntity(byId));
        }
        return toDto(tutor);

    }

    public List<TutorDTO> getAllTutors() {
        List<Tutor> tutors = tutorRepository.findAll();
        List<TutorDTO> tutorDTOs = tutors.stream().map(TutorService::toDto).toList();
        return tutorDTOs;
    }

    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }

    public TutorDTO findById(Long id) {
        Optional<Tutor> byId = tutorRepository.findById(id);
        if(byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }


    private static Tutor toEntity(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        tutor.setId(tutorDTO.getId());
        tutor.setCpf(tutorDTO.getCpf());
        tutor.setNome(tutorDTO.getNome());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setTelefone(tutorDTO.getTelefone());
        if (tutorDTO.getNomesDosAnimais() != null) {
            List<Animal> animais = tutorDTO.getNomesDosAnimais().stream()
                    .map(nome -> {
                        Animal animal = new Animal();
                        animal.setNome(nome);
                        animal.setTutor(tutor);
                        return animal;
                    }).toList();
            tutor.setAnimais(animais);
        }
        return tutor;
    }

    private static TutorDTO toDto(Tutor tutor) {
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setId(tutor.getId());
        tutorDTO.setCpf(tutor.getCpf());
        tutorDTO.setNome(tutor.getNome());
        tutorDTO.setEmail(tutor.getEmail());
        tutorDTO.setTelefone(tutor.getTelefone());
        if (tutor.getAnimais() != null) {
            List<String> nomesDosAnimais = tutor.getAnimais().stream()
                    .map(Animal::getNome)
                    .toList();
            tutorDTO.setNomesDosAnimais(nomesDosAnimais);
        }
        return tutorDTO;
    }
}
