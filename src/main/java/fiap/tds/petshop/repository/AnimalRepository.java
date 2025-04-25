package fiap.tds.petshop.repository;

import fiap.tds.petshop.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT AVG(a.peso) FROM Animal a")
    double pesoMedioAnimais();
}
