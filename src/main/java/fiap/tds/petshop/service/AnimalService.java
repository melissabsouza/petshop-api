package fiap.tds.petshop.service;

import fiap.tds.petshop.dto.AnimalDTO;
import fiap.tds.petshop.entity.Animal;
import fiap.tds.petshop.entity.Tutor;
import fiap.tds.petshop.repository.AnimalRepository;
import fiap.tds.petshop.repository.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private TutorRepository tutorRepository;

   public AnimalDTO saveAnimal(AnimalDTO animalDTO) {
       Animal animal = toEntity(animalDTO);

       if(animalDTO.getId()==null){
           animal = animalRepository.save(animal);
       } else{
           AnimalDTO byId = this.findById(animalDTO.getId());
           byId.setNome(animalDTO.getNome());
           byId.setTipo(animalDTO.getTipo());
           byId.setRaca(animalDTO.getRaca());
           byId.setDataNascimento(animalDTO.getDataNascimento());
           byId.setPeso(animalDTO.getPeso());
           byId.setSexo(animalDTO.getSexo());
           byId.setCastrado(animalDTO.isCastrado());
       }
       if (animalDTO.getTutorCpf() != null) {
           Tutor tutor = tutorRepository.findById(animalDTO.getTutorCpf())
                   .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
           animal.setTutor(tutor);
       }

       animal = animalRepository.save(animal);
       return toDto(animal);
   }
    public List<AnimalDTO> getAllAnimals(){
        List<Animal> animals = animalRepository.findAll();
        List<AnimalDTO> animalDTOs = animals.stream().map(AnimalService::toDto).toList();
        return animalDTOs;
    }
    public void deleteById(Long id){
        animalRepository.deleteById(id);
    }

    public AnimalDTO findById(Long id){
        Optional<Animal> byId = animalRepository.findById(id);
        if(byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("Animal não encontrado");
    }

    private Animal toEntity(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setId(animalDTO.getId());
        animal.setNome(animalDTO.getNome());
        animal.setTipo(animalDTO.getTipo());
        animal.setRaca(animalDTO.getRaca());
        animal.setDataNascimento(animalDTO.getDataNascimento());
        animal.setPeso(animalDTO.getPeso());
        animal.setSexo(animalDTO.getSexo());
        animal.setCastrado(animalDTO.isCastrado());

        if (animalDTO.getTutorCpf() != null) {
            Tutor tutor = tutorRepository.findById(animalDTO.getTutorCpf())
                    .orElseThrow(() -> new RuntimeException("Tutor não encontrado com CPF: " + animalDTO.getTutorCpf()));
            animal.setTutor(tutor);
        }

        return animal;
    }

    private static AnimalDTO toDto(Animal animal) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setNome(animal.getNome());
        animalDTO.setTipo(animal.getTipo());
        animalDTO.setRaca(animal.getRaca());
        animalDTO.setDataNascimento(animal.getDataNascimento());
        animalDTO.setPeso(animal.getPeso());
        animalDTO.setSexo(animal.getSexo());
        animalDTO.setCastrado(animal.isCastrado());
        if (animal.getTutor() != null) {
            animalDTO.setTutorCpf(animal.getTutor().getCpf());
        }

        return animalDTO;
    }
}
