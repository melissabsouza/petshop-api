package fiap.tds.petshop.repository;

import fiap.tds.petshop.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByCpf(String cpf);
    long count();
}
