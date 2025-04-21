package fiap.tds.petshop.entity;

import fiap.tds.petshop.entity.enums.SexoType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min=2, message = "Nome deve ter pelo menos 2 caracteres")
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @NotNull(message = "Tipo não pode ser nulo")
    @Size(min=4, message = "Tipo deve ter pelo menos 4 caracteres")
    private String tipo;

    @NotBlank(message = "Raça é obrigatória")
    @NotNull(message = "Raça não pode ser nula")
    @Size(min=4, message = "Raça deve ter pelo menos 4 caracteres")
    private String raca;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dataNascimento;

    @NotNull(message = "Peso não pode ser nulo")
    @Min(value = 0, message = "peso não pode ser negativo")
    private double peso;

    private SexoType sexo;

    @NotNull(message = "não pode ser nulo")
    private boolean castrado;

    @ManyToOne
    @JoinColumn(name = "tutor_cpf")
    @ToString.Exclude
    private Tutor tutor;
}
