package fiap.tds.petshop.service;

import fiap.tds.petshop.dto.TutorDTO;
import fiap.tds.petshop.entity.Tutor;
import fiap.tds.petshop.repository.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TutorService {

    @Autowired
    private final TutorRepository tutorRepository;

    public TutorDTO saveTutor(TutorDTO tutorDTO) {
        Tutor tutor = toEntity(tutorDTO);

//        if(tutorDTO .getCpf()==null){
//            tutor = tutorRepository.save(tutor);
//        } else{
//            tutor.byId = this.findByCPF(tutorDTO.getCpf());
//
//
       }

        public TutorDTO findById(String cpf) {
            Optional<Tutor> byId = tutorRepository.findById(cpf);
            if(byId.isPresent()) {
                return toDto(byId.get());
            }
            throw new RuntimeException("cpf nao encontrado");
        }

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
        tutorDTO.setCpf(tutor.getCpf()); // Certifique-se que está usando getCpf()
        tutorDTO.setNome(tutor.getNome());
        tutorDTO.setEmail(tutor.getEmail());
        tutorDTO.setTelefone(tutor.getTelefone());
        tutorDTO.setAnimais(tutor.getAnimais());
        return tutorDTO;
    }
}
