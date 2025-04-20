package fiap.tds.petshop.dto;

import fiap.tds.petshop.entity.Tutor;
import fiap.tds.petshop.entity.enums.SexoType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
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

    @NotNull(message = "Idade não pode ser nula")
    @Min(value = 0, message = "a idade não pode ser negativa")
    private int idade;

    @NotNull(message = "Peso não pode ser nulo")
    @Min(value = 0, message = "peso não pode ser negativo")
    private double peso;

    private SexoType sexo;

    @NotNull(message = "não pode ser nulo")
    private boolean castrado;

    @ManyToOne
    @JoinColumn(name = "tutor_cpf")
    private Tutor tutor;
}
