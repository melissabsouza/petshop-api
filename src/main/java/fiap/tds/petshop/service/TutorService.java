package fiap.tds.petshop.service;

import fiap.tds.petshop.dto.TutorDTO;
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

        if (tutorDTO.getCpf() == null || !tutorRepository.existsById(tutorDTO.getCpf())) {
            tutor = tutorRepository.save(tutor);
        } else {
            TutorDTO byId = this.findById(tutorDTO.getCpf());
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

    public void deleteById(String cpf) {
        tutorRepository.deleteById(cpf);
    }

    public TutorDTO findById(String cpf) {
        Optional<Tutor> byId = tutorRepository.findById(cpf);
        if(byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("cpf nao encontrado");
    }


    private static Tutor toEntity(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        tutor.setCpf(tutorDTO.getCpf());
        tutor.setNome(tutorDTO.getNome());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setTelefone(tutorDTO.getTelefone());
        tutor.setAnimais(tutorDTO.getAnimais());
        return tutor;
    }

    private static TutorDTO toDto(Tutor tutor) {
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setCpf(tutor.getCpf());
        tutorDTO.setNome(tutor.getNome());
        tutorDTO.setEmail(tutor.getEmail());
        tutorDTO.setTelefone(tutor.getTelefone());
        tutorDTO.setAnimais(tutor.getAnimais());
        return tutorDTO;
    }
}
